package tk.p4rty.cachemyenderchest.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.p4rty.cachemyenderchest.Main;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin implements ClientPlayPacketListener {
    @Shadow @Final private MinecraftClient client;
    private final ItemStack[] items = new ItemStack[27];

    @Inject(method = "onInventory", at = @At("TAIL"))
    private void onOnInventory(InventoryS2CPacket packet, CallbackInfo ci) {
        if ((this.client.currentScreen instanceof GenericContainerScreen) && this.client.currentScreen.getTitle().equals(new TranslatableTextContent("container.enderchest"))) {
            for (int i = 0; i < packet.getContents().size() - 36; i++) {
                items[i] = packet.getContents().get(i);
            }
            Main.ecinv = new SimpleInventory(items);
        }
    }
    @Inject(method = "onGameJoin", at = @At("TAIL"))
    private void onOnGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        Main.ecinv = new EnderChestInventory();
    }

}
