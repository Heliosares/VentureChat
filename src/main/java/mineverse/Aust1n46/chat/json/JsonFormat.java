package mineverse.Aust1n46.chat.json;

import mineverse.Aust1n46.chat.ClickAction;
import mineverse.Aust1n46.chat.MineverseChat;
import mineverse.Aust1n46.chat.utilities.Format;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public record JsonFormat(String name, int priority, List<JsonAttribute> jsonAttributes) {
    private static final MineverseChat plugin = MineverseChat.getInstance();
    private static HashMap<String, JsonFormat> jsonFormats;

    public static void initialize() {
        jsonFormats = new HashMap<>();
        ConfigurationSection jsonFormatSection = plugin.getConfig().getConfigurationSection("jsonformatting");
        if (jsonFormatSection == null) throw new IllegalArgumentException("No jsonformatting section");
        for (String jsonFormat : jsonFormatSection.getKeys(false)) {
            int priority = jsonFormatSection.getInt(jsonFormat + ".priority", 0);
            List<JsonAttribute> jsonAttributes = new ArrayList<>();
            ConfigurationSection jsonAttributeSection = jsonFormatSection.getConfigurationSection(jsonFormat + ".json_attributes");
            if (jsonAttributeSection != null) {
                for (String attribute : jsonAttributeSection.getKeys(false)) {
                    List<String> hoverText = jsonAttributeSection.getStringList(attribute + ".hover_text");
                    String clickActionText = jsonAttributeSection.getString(attribute + ".click_action", "none");
                    try {
                        ClickAction clickAction = ClickAction.valueOf(clickActionText.toUpperCase());
                        String clickText = jsonAttributeSection.getString(attribute + ".click_text", "");
                        jsonAttributes.add(new JsonAttribute(attribute, hoverText, clickAction, clickText));
                    } catch (IllegalArgumentException | NullPointerException exception) {
                        plugin.getServer().getConsoleSender().sendMessage(Format.FormatStringAll("&8[&eVentureChat&8]&c - Illegal click_action: " + clickActionText + " in jsonFormat: " + jsonFormat));
                    }
                }
            }
            jsonFormats.put(jsonFormat.toLowerCase(), new JsonFormat(jsonFormat, priority, jsonAttributes));
        }
    }

    public static Collection<JsonFormat> getJsonFormats() {
        return jsonFormats.values();
    }

    public static JsonFormat getJsonFormat(String name) {
        return jsonFormats.get(name.toLowerCase());
    }
}
