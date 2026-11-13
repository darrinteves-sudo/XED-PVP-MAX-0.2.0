package com.xed.pvpmax;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import net.minecraft.client.MinecraftClient;

public final class XedConfig {
    private static final String FILE_NAME = "xed-pvp-max.properties";

    public static boolean fpsHud = true;
    public static boolean combatHud = true;
    public static boolean coordinatesHud = false;
    public static boolean compactHud = true;

    private XedConfig() {}

    private static Path configPath() {
        return MinecraftClient.getInstance().runDirectory.toPath()
                .resolve("config")
                .resolve(FILE_NAME);
    }

    public static void load() {
        Path path = configPath();
        if (!Files.exists(path)) {
            save();
            return;
        }

        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(path)) {
            properties.load(input);
            fpsHud = Boolean.parseBoolean(properties.getProperty("fpsHud", "true"));
            combatHud = Boolean.parseBoolean(properties.getProperty("combatHud", "true"));
            coordinatesHud = Boolean.parseBoolean(properties.getProperty("coordinatesHud", "false"));
            compactHud = Boolean.parseBoolean(properties.getProperty("compactHud", "true"));
        } catch (IOException exception) {
            XedPvpMaxClient.LOGGER.warn("Could not read XED PvP MAX config; using defaults.", exception);
        }
    }

    public static void save() {
        Path path = configPath();
        Properties properties = new Properties();
        properties.setProperty("fpsHud", Boolean.toString(fpsHud));
        properties.setProperty("combatHud", Boolean.toString(combatHud));
        properties.setProperty("coordinatesHud", Boolean.toString(coordinatesHud));
        properties.setProperty("compactHud", Boolean.toString(compactHud));
        properties.setProperty("configVersion", "1");

        try {
            Files.createDirectories(path.getParent());
            try (OutputStream output = Files.newOutputStream(path)) {
                properties.store(output, "XED PvP MAX settings - do not edit while Minecraft is running");
            }
        } catch (IOException exception) {
            XedPvpMaxClient.LOGGER.warn("Could not save XED PvP MAX config.", exception);
        }
    }
}
