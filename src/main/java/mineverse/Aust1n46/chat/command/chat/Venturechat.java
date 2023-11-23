package mineverse.Aust1n46.chat.command.chat;

import org.bukkit.command.Command;
//import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
//import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
//import org.bukkit.entity.Player;

import mineverse.Aust1n46.chat.MineverseChat;
import mineverse.Aust1n46.chat.localization.InternalMessage;

public class Venturechat extends Command {
	private final MineverseChat plugin = MineverseChat.getInstance();
	
	public Venturechat() {
		super("venturechat");
	}
	
	@Override
	public boolean execute(CommandSender sender, String command, String[] args) {
		sender.sendMessage(InternalMessage.VENTURECHAT_VERSION.toString()
				.replace("{version}", plugin.getDescription().getVersion()));
		sender.sendMessage(InternalMessage.VENTURECHAT_AUTHOR.toString());
		return true;
    }

}
