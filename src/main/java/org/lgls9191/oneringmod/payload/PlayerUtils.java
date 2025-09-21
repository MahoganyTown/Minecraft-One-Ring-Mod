package org.lgls9191.oneringmod.payload;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class PlayerUtils {
    public static boolean hasItemInHotbar(PlayerEntity player, ItemStack itemToCheck) {
        PlayerInventory playerInventory = player.getInventory();

        // Loop through the hotbar slots (0-8)
        for (int i = 0; i < 9; i++) {
            ItemStack stackInHotbar = playerInventory.getStack(i);
            // Check if the item in the hotbar matches the item to check
            if (ItemStack.areItemsEqual(stackInHotbar, itemToCheck)) {
                return true; // Item found in hotbar
            }
        }
        return false; // Item not found in hotbar
    }
}
