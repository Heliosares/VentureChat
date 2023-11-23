package mineverse.Aust1n46.chat.database;

import java.util.HashMap;
import java.util.UUID;

public class TemporaryDataInstance {
    private static final HashMap<UUID, TemporaryDataInstance> temporaryDataInstances = new HashMap<>();
    private final UUID uuid;
    private int messagePackets;

    private TemporaryDataInstance(UUID uuid) {
        this.uuid = uuid;
    }

    public static UUID createTemporaryDataInstance() {
        UUID uuid = UUID.randomUUID();
        temporaryDataInstances.put(uuid, new TemporaryDataInstance(uuid));
        return uuid;
    }

    public static TemporaryDataInstance getTemporaryDataInstance(UUID uuid) {
        return temporaryDataInstances.get(uuid);
    }

    public void incrementMessagePackets() {
        this.messagePackets++;
    }

    public void destroyInstance() {
        temporaryDataInstances.remove(uuid);
    }

    public int getMessagePackets() {
        return this.messagePackets;
    }
}
