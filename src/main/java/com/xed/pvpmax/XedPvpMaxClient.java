package com.xed.pvpmax;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class XedPvpMaxClient implements ClientModInitializer {
    public static final String MOD_ID = "xed_pvp_max";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        XedConfig.load();

        KeyBinding.Category category = KeyBinding.Category.create(
                Identifier.of(MOD_ID, "main")
        );
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.xed_pvp_max.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new XedPvpMaxScreen(null));
                }
            }
        });

        HudElementRegistry.addLast(
                Identifier.of(MOD_ID, "pvp_hud"),
                (drawContext, tickCounter) -> XedHud.render(MinecraftClient.getInstance(), drawContext)
        );

        LOGGER.info("XED PvP MAX 0.2.0 initialized. Press Right Shift to open settings.");
    }
}
