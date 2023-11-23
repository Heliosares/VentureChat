package mineverse.Aust1n46.chat.command.chat;

import mineverse.Aust1n46.chat.api.MineverseChatAPI;
import mineverse.Aust1n46.chat.api.MineverseChatPlayer;
import mineverse.Aust1n46.chat.localization.LocalizedMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Forceall extends Command {
    public Forceall() {
        super("forceall");
    }

    @Override
    public boolean execute(CommandSender sender, String command, String[] args) {
        if (sender.hasPermission("venturechat.forceall")) {
            if (args.length < 1) {
                sender.sendMessage(LocalizedMessage.COMMAND_INVALID_ARGUMENTS.toString().replace("{command}", "/forceall").replace("{args}", "[message]"));
                return true;
            }
            StringBuilder forcemsg = new StringBuilder();
            for (int x = 0; x < args.length; x++) {
                if (!args[x].isEmpty()) {
                    forcemsg.append(args[x]).append(" ");
                }
            }
            sender.sendMessage(LocalizedMessage.FORCE_ALL.toString().replace("{message}", forcemsg.toString()));
            for (MineverseChatPlayer player : MineverseChatAPI.getOnlineMineverseChatPlayers()) {
                player.getPlayer().chat(forcemsg.toString());
            }
            return true;
        }
        sender.sendMessage(LocalizedMessage.COMMAND_NO_PERMISSION.toString());
        return true;
    }
}
