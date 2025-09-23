package org.lgls9191.oneringmod.mixin;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.lgls9191.oneringmod.items.ModItems;
import org.lgls9191.oneringmod.items.OneRingItem;
import org.lgls9191.oneringmod.networking.PlayerStateTrackerServer;
import org.lgls9191.oneringmod.networking.UseRingS2CPayload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "dropItem", at = @At(value = "HEAD"))
    public void dropItem(ItemStack stack, boolean dropAtSelf, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
//        if (stack != null) {
//            // Stack is not empty
//            ItemStack ring = new ItemStack(ModItems.ONE_RING);
//
//            if (ItemStack.areItemsEqual(stack, ring)) {
//                // The item dropped is a ring
//                OneRingItem ringItem = (OneRingItem) stack.getItem();
//                // Ring is dropped, just deactivate immediately
//                ringItem.deactivate();
////                // Update server-side player tracker
////                PlayerStateTrackerServer.updatePlayerState(user.getUuidAsString(), false);
//
//            }
//        }



        if (stack != null) {
            // Stack is not empty
            ItemStack ring = new ItemStack(ModItems.ONE_RING);

            if (ItemStack.areItemsEqual(stack, ring)) {
                // The item dropped is a ring
                OneRingItem ringItem = (OneRingItem) stack.getItem();
                // Ring is dropped, just deactivate immediately
                ringItem.deactivate();

                PlayerEntity pe = (PlayerEntity)(Object)this;

                // Update server-side player tracker
                PlayerStateTrackerServer.updatePlayerState(pe.getUuidAsString(), false);

                // Send packet to server
                UseRingS2CPayload payload = new UseRingS2CPayload(pe.getUuidAsString(), false);

//                ServerLifecycleEvents.SERVER_STARTED.register(server -> {
//                    // Get all players when the server starts
//                    PlayerLookup.all(server).forEach(player -> {
//                        System.out.println(pe.getUuidAsString() + " -> ");
//                        ServerPlayNetworking.send(player, payload);
//                    });
//                });
                var server = pe.getServer();
                if (server != null) {
                    PlayerLookup.all(server).forEach(player -> {
                        System.out.println(pe.getUuidAsString() + " -> ");
                        ServerPlayNetworking.send(player, payload);
                    });
                }
            }
        }
    }
}
