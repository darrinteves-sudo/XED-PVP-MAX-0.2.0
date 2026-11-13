package com.xed.pvpmax;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;

public final class XedHud {
    private static final int WHITE = 0xFFFFFFFF;
    private static final int RED = 0xFFFF5555;
    private static final int PANEL = 0x99090000;

    private XedHud() {}

    public static void render(MinecraftClient client, DrawContext context) {
        ClientPlayerEntity player = client.player;
        if (player == null || client.world == null || client.options.hudHidden) return;

        int y = 8;
        int width = XedConfig.compactHud ? 170 : 205;

        if (XedConfig.fpsHud) {
            context.fill(7, y - 3, 7 + width, y + 11, PANEL);
            context.fill(7, y - 3, 9, y + 11, RED);
            context.drawTextWithShadow(client.textRenderer,
                    "XED  |  " + client.getCurrentFps() + " FPS", 13, y, WHITE);
            y += 17;
        }

        if (XedConfig.combatHud) {
            float health = player.getHealth();
            int armor = player.getArmor();
            context.fill(7, y - 3, 7 + width, y + 11, PANEL);
            context.fill(7, y - 3, 9, y + 11, RED);
            String status = String.format(java.util.Locale.ROOT, "HP %.1f/%.0f  |  ARM %d",
                    health, player.getMaxHealth(), armor);
            context.drawTextWithShadow(client.textRenderer, status, 13, y, WHITE);
            y += 17;
        }

        if (XedConfig.coordinatesHud) {
            String coords = String.format(java.util.Locale.ROOT, "XYZ %d  %d  %d",
                    player.getBlockX(), player.getBlockY(), player.getBlockZ());
            context.fill(7, y - 3, 7 + width, y + 11, PANEL);
            context.fill(7, y - 3, 9, y + 11, RED);
            context.drawTextWithShadow(client.textRenderer, coords, 13, y, WHITE);
        }
    }
}
