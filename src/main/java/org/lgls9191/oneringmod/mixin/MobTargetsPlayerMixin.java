package org.lgls9191.oneringmod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.lgls9191.oneringmod.items.ModItems;
import org.lgls9191.oneringmod.payload.PlayerUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MobTargetsPlayerMixin {
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

                if (PlayerUtils.hasItemInHotbar(player, ring)) {
                    // Make the mob not target the player if ring active
                    this.target = null;
                }
            }
        }
    }
}
