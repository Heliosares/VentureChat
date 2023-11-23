package mineverse.Aust1n46.chat.proxy;

import java.util.List;

public interface VentureChatProxySource {
    void sendPluginMessage(String serverName, byte[] data);

    VentureChatProxyServer getServer(String serverName);

    void sendConsoleMessage(String message);

    List<VentureChatProxyServer> getServers();

    boolean isOfflineServerAcknowledgementSet();
}
