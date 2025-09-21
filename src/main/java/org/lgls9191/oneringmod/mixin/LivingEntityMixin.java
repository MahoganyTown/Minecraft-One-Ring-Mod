package org.lgls9191.oneringmod.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.lgls9191.oneringmod.items.ModItems;
import org.lgls9191.oneringmod.items.OneRingItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "dropItem", at = @At(value = "HEAD"))
    public void dropItem(ItemStack stack, boolean dropAtSelf, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (stack != null) {
            // Stack is not empty
            ItemStack ring = new ItemStack(ModItems.ONE_RING);

            if (ItemStack.areItemsEqual(stack, ring)) {
                // The item dropped is a ring
                OneRingItem ringItem = (OneRingItem) stack.getItem();
                // Ring is dropped, just deactivate immediately
                ringItem.deactivate();
            }
        }
    }
}
