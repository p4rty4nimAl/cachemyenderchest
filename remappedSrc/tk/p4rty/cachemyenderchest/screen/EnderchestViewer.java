package tk.p4rty.cachemyenderchest.screen;

import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.text.Text;

public class EnderchestViewer extends GenericContainerScreen {

    public EnderchestViewer(GenericContainerScreenHandler handler, PlayerInventory pinv, Text title) {
        super(handler, pinv, title);
    }
    @Override
    protected void init() {
        super.init();
        this.x = (this.width - this.backgroundWidth) / 2;
        this.y = (this.height - this.backgroundHeight) / 2;
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return true;
    }
}
