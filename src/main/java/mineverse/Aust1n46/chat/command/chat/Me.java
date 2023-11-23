package mineverse.Aust1n46.chat.command.chat;

import mineverse.Aust1n46.chat.api.MineverseChatAPI;
import mineverse.Aust1n46.chat.localization.LocalizedMessage;
import mineverse.Aust1n46.chat.utilities.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Me extends Command {
    public Me() {
        super("me");
    }

    @Override
    public boolean execute(CommandSender sender, @NotNull String command, String[] args) {
        if (sender.hasPermission("venturechat.me")) {
            if (args.length > 0) {
                StringBuilder msg = new StringBuilder();
                for (String arg : args)
                    if (!arg.isEmpty())
                        msg.append(" ").append(arg);
                if (sender instanceof Player && MineverseChatAPI.getOnlineMineverseChatPlayer((Player) sender).hasFilter()) {
                    msg = new StringBuilder(Format.FilterChat(msg.toString()));
                }
                if (sender.hasPermission("venturechat.color.legacy")) {
                    msg = new StringBuilder(Format.FormatStringLegacyColor(msg.toString()));
                }
                if (sender.hasPermission("venturechat.color"))
                    msg = new StringBuilder(Format.FormatStringColor(msg.toString(), sender.hasPermission("venturechat.color.hex")));
                if (sender.hasPermission("venturechat.format"))
                    msg = new StringBuilder(Format.FormatString(msg.toString(), sender.hasPermission("venturechat.format.magic")));
                if (sender instanceof Player p) {
					Format.broadcastToServer("* " + p.getDisplayName() + msg);
                    return true;
                }
                Format.broadcastToServer("* " + sender.getName() + msg);
                return true;
            }
            sender.sendMessage(LocalizedMessage.COMMAND_INVALID_ARGUMENTS.toString().replace("{command}", "/me").replace("{args}", "[message]"));
            return true;
        }
        sender.sendMessage(LocalizedMessage.COMMAND_NO_PERMISSION.toString());
        return true;
    }
}
