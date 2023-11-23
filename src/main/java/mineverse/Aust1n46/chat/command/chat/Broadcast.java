package mineverse.Aust1n46.chat.command.chat;

import mineverse.Aust1n46.chat.MineverseChat;
import mineverse.Aust1n46.chat.localization.LocalizedMessage;
import mineverse.Aust1n46.chat.utilities.Format;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

public class Broadcast extends Command {
    private final MineverseChat plugin = MineverseChat.getInstance();

    public Broadcast() {
        super("broadcast");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String command, String[] args) {
        ConfigurationSection bs = plugin.getConfig().getConfigurationSection("broadcast");
        String broadcastColor = bs.getString("color", "white");
        String broadcastPermissions = bs.getString("permissions", "None");
        String broadcastDisplayTag = Format.FormatStringAll(bs.getString("displaytag", "[Broadcast]"));
        if (broadcastPermissions.equalsIgnoreCase("None") || sender.hasPermission(broadcastPermissions)) {
            if (args.length > 0) {
                StringBuilder bc = new StringBuilder();
                for (String arg : args) {
                    if (!arg.isEmpty())
                        bc.append(arg).append(" ");
                }
                bc = new StringBuilder(Format.FormatStringAll(bc.toString()));
                Format.broadcastToServer(broadcastDisplayTag + ChatColor.valueOf(broadcastColor.toUpperCase()) + " " + bc);
                return true;
            } else {
                sender.sendMessage(LocalizedMessage.COMMAND_INVALID_ARGUMENTS.toString().replace("{command}", "/broadcast").replace("{args}", "[msg]"));
                return true;
            }
        } else {
            sender.sendMessage(LocalizedMessage.COMMAND_NO_PERMISSION.toString());
            return true;
        }
    }
}
