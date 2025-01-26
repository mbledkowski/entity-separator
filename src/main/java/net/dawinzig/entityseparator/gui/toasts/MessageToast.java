package net.dawinzig.entityseparator.gui.toasts;

import net.dawinzig.entityseparator.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageToast implements Toast {
    private final Minecraft client;
    private final Component text;
    private final Level level;

    public MessageToast(Minecraft client, Component text, Level level) {
        this.client = client;
        this.text = text;
        this.level = level;
    }

    private Visibility visibility = Visibility.HIDE;

    @Override
    public Visibility getWantedVisibility() {
        return this.visibility;
    }

    @Override
    public void update(ToastManager toastManager, long l) {

    }

    @Override
    public void render(GuiGraphics context, Font font, long startTime) {
        context.blitSprite(Resources.IDShort.TOAST_BACKGROUND, 0, 0, this.width(), this.height());
        context.vLine(4, 3, this.height()-4, level.color);
        context.drawString(font, text, 15, 12, 16777215, false);
        int showTime = 2000;
        this.visibility = startTime > showTime ? Visibility.HIDE : Visibility.SHOW;
    }

    @Override
    public int width() {
        return client.font.width(text) + 27;
    }

    public enum Level {
        INFO(ARGB.color(255,200,200,200)),
        ERROR(ARGB.color(255,255,50,50));

        public final int color;

        Level(Integer color) {
            this.color = Objects.requireNonNullElse(color, 11184810);
        }
    }
}
