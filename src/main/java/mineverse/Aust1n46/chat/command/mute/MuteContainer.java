package mineverse.Aust1n46.chat.command.mute;

public class MuteContainer {
    private final String channel;
    private final String reason;
    private final long duration;

    public MuteContainer(String channel) {
        this(channel, 0, "");
    }

    public MuteContainer(String channel, long duration) {
        this(channel, duration, "");
    }

    public MuteContainer(String channel, String reason) {
        this(channel, 0, reason);
    }

    public MuteContainer(String channel, long duration, String reason) {
        this.channel = channel;
        this.reason = reason;
        this.duration = duration;
    }

    public boolean hasReason() {
        return !reason.isEmpty();
    }

    public boolean hasDuration() {
        return duration > 0;
    }

    public String getChannel() {
        return channel;
    }

    public String getReason() {
        return reason;
    }

    public long getDuration() {
        return duration;
    }
}
