package tk.p4rty.cachemyenderchest;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.text.TranslatableTextContent;
import org.lwjgl.glfw.GLFW;
import tk.p4rty.cachemyenderchest.screen.EnderchestViewer;
import tk.p4rty.cachemyenderchest.screen.EnderchestViewerScreenHandler;

import static net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding;

public class Main implements ClientModInitializer {
    public static KeyBinding CMECKeyBinding =  new KeyBinding("cmec.kb.openmenu", GLFW.GLFW_KEY_Y, "cmec.kb.category");
    public static SimpleInventory ecinv = new EnderChestInventory();
    @Override
    public void onInitializeClient() {
        registerKeyBinding(CMECKeyBinding);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (CMECKeyBinding.wasPressed()) {
                client.setScreen(new EnderchestViewer(new EnderchestViewerScreenHandler(
                        8080,
                        client.player.getInventory(),
                        ecinv),
                        client.player.getInventory(), new TranslatableTextContent("cmec.title.clientcache", new TranslatableTextContent("container.enderchest"))));
            }
        });
    }
}
