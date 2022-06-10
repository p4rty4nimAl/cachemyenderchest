package tk.p4rty.cachemyenderchest.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tk.p4rty.cachemyenderchest.Main;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Shadow
    protected EnderChestInventory enderChestInventory;

    @Inject(method = "getEnderChestInventory", at = @At("HEAD"))
    private void onGetEnderChestInventory(CallbackInfoReturnable<EnderChestInventory> cir) {
        Main.ecinv = this.enderChestInventory;
    }
}
