package mineverse.Aust1n46.chat.alias;

import mineverse.Aust1n46.chat.MineverseChat;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public record Alias(String name, int arguments, List<String> components, String permission) {
    private static final MineverseChat plugin = MineverseChat.getInstance();
    private static List<Alias> aliases;

    public Alias(String name, int arguments, List<String> components, String permission) {
        this.name = name;
        this.arguments = arguments;
        this.components = components;
        this.permission = "venturechat." + permission;
    }

    public static void initialize() {
        aliases = new ArrayList<>();
        ConfigurationSection cs = plugin.getConfig().getConfigurationSection("alias");
        if (cs == null) throw new IllegalArgumentException("No alias section");
        for (String key : cs.getKeys(false)) {
            int arguments = cs.getInt(key + ".arguments", 0);
            List<String> components = cs.getStringList(key + ".components");
            String permissions = cs.getString(key + ".permissions", "None");
            aliases.add(new Alias(key, arguments, components, permissions));
        }
    }

    public static List<Alias> getAliases() {
        return aliases;
    }

    public boolean hasPermission() {
        return !permission.equalsIgnoreCase("venturechat.none");
    }
}
