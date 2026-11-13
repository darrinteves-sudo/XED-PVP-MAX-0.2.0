package com.xed.pvpmax;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class XedPvpMaxScreen extends Screen {
    private final Screen parent;
    private static final int RED = 0xFFE53935;
    private static final int MUTED = 0xFFB8B8B8;

    public XedPvpMaxScreen(Screen parent) {
        super(Text.literal("XED PvP MAX"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int buttonWidth = Math.min(230, this.width - 32);
        int x = (this.width - buttonWidth) / 2;
        int y = Math.max(64, this.height / 2 - 62);
        int gap = 25;

        addToggle(x, y, buttonWidth, "FPS HUD", () -> XedConfig.fpsHud,
                value -> XedConfig.fpsHud = value);
        addToggle(x, y + gap, buttonWidth, "Combat HUD", () -> XedConfig.combatHud,
                value -> XedConfig.combatHud = value);
        addToggle(x, y + gap * 2, buttonWidth, "Coordinates HUD", () -> XedConfig.coordinatesHud,
                value -> XedConfig.coordinatesHud = value);
        addToggle(x, y + gap * 3, buttonWidth, "Compact HUD", () -> XedConfig.compactHud,
                value -> XedConfig.compactHud = value);

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Save & Close"), button -> {
            XedConfig.save();
            if (this.client != null) this.client.setScreen(parent);
        }).dimensions(x, y + gap * 4 + 4, buttonWidth, 20).build());
    }

    private void addToggle(int x, int y, int width, String label,
                           java.util.function.BooleanSupplier getter,
                           java.util.function.Consumer<Boolean> setter) {
        ButtonWidget button = ButtonWidget.builder(toggleText(label, getter.getAsBoolean()), clicked -> {
            boolean next = !getter.getAsBoolean();
            setter.accept(next);
            clicked.setMessage(toggleText(label, next));
            XedConfig.save();
        }).dimensions(x, y, width, 20).build();
        this.addDrawableChild(button);
    }

    private static Text toggleText(String label, boolean enabled) {
        return Text.literal(label + ": " + (enabled ? "ON" : "OFF"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.fill(0, 0, this.width, this.height, 0xB9000000);
        int center = this.width / 2;
        context.fill(center - 132, 20, center + 132, 47, 0xFF151515);
        context.fill(center - 132, 20, center - 127, 47, RED);
        context.drawCenteredTextWithShadow(this.textRenderer, "XED PvP MAX  0.2.0", center, 29, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, "Client settings • saved automatically", center,
                51, MUTED);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, "Open menu: Right Shift", center,
                this.height - 18, MUTED);
    }

    @Override
    public void close() {
        XedConfig.save();
        if (this.client != null) this.client.setScreen(parent);
    }
}
