package tk.p4rty.cachemyenderchest.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.text.TranslatableTextContent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tk.p4rty.cachemyenderchest.Main;

@Mixin(Screen.class)
public abstract class ScreenMixin extends AbstractParentElement implements Drawable {
    @Shadow @Nullable protected MinecraftClient client;

    @Inject(method = "keyPressed", at = @At("HEAD"))
    public void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if ((this.client.currentScreen instanceof GenericContainerScreen) && this.client.currentScreen.getTitle().equals(new TranslatableTextContent("container.enderchest"))) {
            if (keyCode == 256 || keyCode == 69) {
                Main.ecinv = (SimpleInventory) ((GenericContainerScreen) this.client.currentScreen).getScreenHandler().getInventory();
            }
        }
    }
}
