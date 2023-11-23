package mineverse.Aust1n46.chat.api.events;

import mineverse.Aust1n46.chat.api.MineverseChatPlayer;
import mineverse.Aust1n46.chat.channel.ChatChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Event called when a message has been sent to a channel.
 * This event can not be cancelled.
 *
 * @author Aust1n46
 */
public class VentureChatEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final MineverseChatPlayer mcp;
    private final String username;
    private final String nickname;
    private final String playerPrimaryGroup;
    private final ChatChannel channel;
    private final Set<Player> recipients;
    private final int recipientCount; //For not counting vanished players
    private final String format;
    private final String chat;
    private final String globalJSON;
    private final int hash;
    private final boolean bungee;

    public VentureChatEvent(MineverseChatPlayer mcp, String username, String nickname, String playerPrimaryGroup, ChatChannel channel, Set<Player> recipients, int recipientCount, String format, String chat, String globalJSON, int hash, boolean bungee) {
        super(true);
        this.mcp = mcp;
        this.username = username;
        this.nickname = nickname;
        this.playerPrimaryGroup = playerPrimaryGroup;
        this.channel = channel;
        this.recipients = recipients;
        this.recipientCount = recipientCount;
        this.format = format;
        this.chat = chat;
        this.globalJSON = globalJSON;
        this.hash = hash;
        this.bungee = bungee;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public MineverseChatPlayer getMineverseChatPlayer() {
        return this.mcp;
    }

    public String getUsername() {
        return this.username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPlayerPrimaryGroup() {
        return this.playerPrimaryGroup;
    }

    public ChatChannel getChannel() {
        return this.channel;
    }

    public Set<Player> getRecipients() {
        return this.recipients;
    }

    //Could be lower than the total number of recipients because vanished players are not counted
    public int getRecipientCount() {
        return this.recipientCount;
    }

    public String getFormat() {
        return this.format;
    }

    public String getChat() {
        return this.chat;
    }

    public String getConsoleChat() {
        return this.format + this.chat;
    }

    public String getGlobalJSON() {
        return this.globalJSON;
    }

    public int getHash() {
        return this.hash;
    }

    public boolean isBungee() {
        return this.bungee;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}