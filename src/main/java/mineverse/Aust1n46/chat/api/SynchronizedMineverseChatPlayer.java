package mineverse.Aust1n46.chat.api;

import mineverse.Aust1n46.chat.command.mute.MuteContainer;

import java.util.*;

public class SynchronizedMineverseChatPlayer {
    private final UUID uuid;
    private final Set<String> listening;
    private final HashMap<String, MuteContainer> mutes;
    private final Set<UUID> ignores;
    private final List<String> messageData = new ArrayList<>();
    private int messagePackets;
    private boolean spy;
    private boolean messageToggle;

    private boolean vanished;

    public SynchronizedMineverseChatPlayer(UUID uuid, Set<String> listening, HashMap<String, MuteContainer> mutes, Set<UUID> ignores, boolean spy, boolean messageToggle, boolean vanished) {
        this.uuid = uuid;
        this.listening = listening;
        this.mutes = mutes;
        this.ignores = ignores;
        this.spy = spy;
        this.messageToggle = messageToggle;
        this.vanished = vanished;
    }

    public SynchronizedMineverseChatPlayer(UUID uuid) {
        this.uuid = uuid;
        listening = new HashSet<>();
        mutes = new HashMap<>();
        ignores = new HashSet<>();
        spy = false;
        messageToggle = true;
    }

    public void addData(String s) {
        this.messageData.add(s);
    }

    public void clearMessageData() {
        this.messageData.clear();
    }

    public void incrementMessagePackets() {
        this.messagePackets++;
    }

    public void clearMessagePackets() {
        this.messagePackets = 0;
    }

    public void addIgnore(SynchronizedMineverseChatPlayer smcp) {
        this.ignores.add(smcp.getUUID());
    }

    public void removeIgnore(SynchronizedMineverseChatPlayer smcp) {
        this.ignores.remove(smcp.getUUID());
    }

    public void addMute(String channel, long time, String reason) {
        mutes.put(channel, new MuteContainer(channel, time, reason));
    }

    public void clearMutes() {
        this.mutes.clear();
    }

    public void addListening(String channel) {
        this.listening.add(channel);
    }

    public void removeListening(String channel) {
        this.listening.remove(channel);
    }

    public List<String> getMessageData() {
        return this.messageData;
    }

    public int getMessagePackets() {
        return this.messagePackets;
    }

    public Set<UUID> getIgnores() {
        return this.ignores;
    }

    public Collection<MuteContainer> getMutes() {
        return this.mutes.values();
    }

    public Set<String> getListening() {
        return this.listening;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public boolean isSpy() {
        return this.spy;
    }

    public void setSpy(boolean spy) {
        this.spy = spy;
    }

    public boolean getMessageToggle() {
        return this.messageToggle;
    }

    public void setMessageToggle(boolean messageToggle) {
        this.messageToggle = messageToggle;
    }

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(boolean vanished) {
        this.vanished = vanished;
    }
}