package org.lgls9191.oneringmod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.lgls9191.oneringmod.items.ModItems;
import org.lgls9191.oneringmod.items.OneRingItem;
import org.lgls9191.oneringmod.payload.PlayerUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {
    @Shadow
    private LivingEntity target;

    @Inject(method = "setTarget", at = @At(value = "TAIL"))
    public void disableSetTarget(LivingEntity target, CallbackInfo ci) {
        // Make sure target is not null, else game crashes
        if (target != null) {
            // Is the target is the player, then verify inventory
            if (target.isPlayer()) {
                PlayerEntity player = (PlayerEntity) target;
                ItemStack ring = new ItemStack(ModItems.ONE_RING);
                ItemStack playerItem = PlayerUtils.findItemInHotbar(player, ring);

                if (playerItem != null) {
                    // The player has a ring in the hotbar
                    OneRingItem ringItem = (OneRingItem) playerItem.getItem();

                    if (ringItem.isActive()) {
                        // Is active, so mobs should not see the player
                        this.target = null;
                    }
                }
            }
        }
    }
}
