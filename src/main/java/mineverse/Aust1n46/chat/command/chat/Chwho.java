package mineverse.Aust1n46.chat.command.chat;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import mineverse.Aust1n46.chat.MineverseChat;
import mineverse.Aust1n46.chat.api.MineverseChatAPI;
import mineverse.Aust1n46.chat.api.MineverseChatPlayer;
import mineverse.Aust1n46.chat.channel.ChatChannel;
import mineverse.Aust1n46.chat.localization.LocalizedMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static mineverse.Aust1n46.chat.MineverseChat.LINE_LENGTH;

public class Chwho extends Command {
    private final MineverseChat plugin = MineverseChat.getInstance();

    public Chwho() {
        super("chwho");
    }

    @Override
    public boolean execute(CommandSender sender, @NotNull String command, String[] args) {
        StringBuilder playerlist = new StringBuilder();
        if (sender.hasPermission("venturechat.chwho")) {
            if (args.length > 0) {
                ChatChannel channel = ChatChannel.getChannel(args[0]);
                if (channel != null) {
                    if (channel.hasPermission()) {
                        if (!sender.hasPermission(channel.getPermission())) {
                            MineverseChatPlayer mcp = MineverseChatAPI.getOnlineMineverseChatPlayer(((Player) sender));
                            mcp.removeListening(channel.getName());
                            mcp.getPlayer().sendMessage(LocalizedMessage.CHANNEL_NO_PERMISSION_VIEW.toString());
                            return true;
                        }
                    }

                    if (channel.getBungee() && sender instanceof Player) {
                        MineverseChatPlayer mcp = MineverseChatAPI.getOnlineMineverseChatPlayer((Player) sender);
                        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                        DataOutputStream out = new DataOutputStream(byteOutStream);
                        try {
                            out.writeUTF("Chwho");
                            out.writeUTF("Get");
                            out.writeUTF(mcp.getUUID().toString());
                            out.writeUTF(channel.getName());
                            mcp.getPlayer().sendPluginMessage(plugin, MineverseChat.PLUGIN_MESSAGING_CHANNEL, byteOutStream.toByteArray());
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    }

                    PluginManager pluginManager = plugin.getServer().getPluginManager();
                    long linecount = LINE_LENGTH;
                    for (MineverseChatPlayer p : MineverseChatAPI.getOnlineMineverseChatPlayers()) {
                        if (p.getListening().contains(channel.getName())) {
                            if (sender instanceof Player) {
                                if (!((Player) sender).canSee(p.getPlayer())) {
                                    continue;
                                }
                            }
                            if (channel.hasDistance() && sender instanceof Player) {
                                if (!this.isPlayerWithinDistance((Player) sender, p.getPlayer(), channel.getDistance())) {
                                    continue;
                                }
                            }
                            if (plugin.getConfig().getBoolean("enable_towny_channel") && pluginManager.isPluginEnabled("Towny") && sender instanceof Player) {
                                try {
                                    TownyUniverse towny = TownyUniverse.getInstance();
                                    if (channel.getName().equalsIgnoreCase("Town")) {
                                        Resident r = towny.getResident(p.getName());
                                        Resident pp = towny.getResident(sender.getName());
                                        if (!pp.hasTown()) {
                                            if (playerlist.length() + p.getName().length() > linecount) {
                                                playerlist.append("\n");
                                                linecount = linecount + LINE_LENGTH;
                                            }
                                            if (!p.isMuted(channel.getName())) {
                                                playerlist.append(ChatColor.WHITE).append(p.getName());
                                            } else {
                                                playerlist.append(ChatColor.RED).append(p.getName());
                                            }
                                            playerlist.append(ChatColor.WHITE + ", ");
                                            break;
                                        } else if (!r.hasTown()) {
                                            continue;
                                        } else if (!(r.getTown().getName().equals(pp.getTown().getName()))) {
                                            continue;
                                        }
                                    }
                                    if (channel.getName().equalsIgnoreCase("Nation")) {
                                        Resident r = towny.getResident(p.getName());
                                        Resident pp = towny.getResident(sender.getName());
                                        if (!pp.hasNation()) {
                                            if (playerlist.length() + p.getName().length() > linecount) {
                                                playerlist.append("\n");
                                                linecount = linecount + LINE_LENGTH;
                                            }
                                            if (!p.isMuted(channel.getName())) {
                                                playerlist.append(ChatColor.WHITE).append(p.getName());
                                            } else {
                                                playerlist.append(ChatColor.RED).append(p.getName());
                                            }
                                            playerlist.append(ChatColor.WHITE + ", ");
                                            break;
                                        } else if (!r.hasNation()) {
                                            continue;
                                        } else if (!(r.getTown().getNation().getName().equals(pp.getTown().getNation().getName()))) {
                                            continue;
                                        }
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            if (playerlist.length() + p.getName().length() > linecount) {
                                playerlist.append("\n");
                                linecount = linecount + LINE_LENGTH;
                            }
                            if (!p.isMuted(channel.getName())) {
                                playerlist.append(ChatColor.WHITE).append(p.getName());
                            } else {
                                playerlist.append(ChatColor.RED).append(p.getName());
                            }
                            playerlist.append(ChatColor.WHITE + ", ");
                        }
                    }
                    if (playerlist.length() > 2) {
                        playerlist = new StringBuilder(playerlist.substring(0, playerlist.length() - 2));
                    }
                    sender.sendMessage(LocalizedMessage.CHANNEL_PLAYER_LIST_HEADER.toString().replace("{channel_color}", (channel.getColor())).replace("{channel_name}",
                            channel.getName()));
                    sender.sendMessage(playerlist.toString());
                    return true;
                } else {
                    sender.sendMessage(LocalizedMessage.INVALID_CHANNEL.toString().replace("{args}", args[0]));
                    return true;
                }
            } else {
                sender.sendMessage(LocalizedMessage.COMMAND_INVALID_ARGUMENTS.toString().replace("{command}", "/chwho").replace("{args}", "[channel]"));
                return true;
            }
        } else {
            sender.sendMessage(LocalizedMessage.COMMAND_NO_PERMISSION.toString());
            return true;
        }
    }

    private boolean isPlayerWithinDistance(Player p1, Player p2, double Distance) {
        double chDistance = Distance;
        Location locreceip;
        Location locsender = p1.getLocation();
        Location diff;
        if (chDistance > (double) 0) {
            locreceip = p2.getLocation();
            if (locreceip.getWorld() == p1.getWorld()) {
                diff = locreceip.subtract(locsender);
                return !(Math.abs(diff.getX()) > chDistance) && !(Math.abs(diff.getZ()) > chDistance) && !(Math.abs(diff.getY()) > chDistance);
            } else {
                return false;
            }
        }
        return true;
    }
}
