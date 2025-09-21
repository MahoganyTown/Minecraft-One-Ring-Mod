package org.lgls9191.oneringmod.payload;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.lgls9191.oneringmod.items.ModItems;
import org.lgls9191.oneringmod.items.OneRingItem;

public class PlayerUtils {
    public static ItemStack findItemInHotbar(PlayerInventory inventory, ItemStack itemToCheck) {
        // Loop through the hotbar slots (0-8)
        for (int i = 0; i < 9; i++) {
            ItemStack stackInHotbar = inventory.getStack(i);
            // Check if the item in the hotbar matches the item to check
            if (ItemStack.areItemsEqual(stackInHotbar, itemToCheck)) {
                return stackInHotbar;
            }
        }
        return null;
    }

    public static boolean isRingInHotbarActive(PlayerInventory inventory) {
        ItemStack ring = new ItemStack(ModItems.ONE_RING);
        ItemStack playerItem = PlayerUtils.findItemInHotbar(inventory, ring);

        if (playerItem != null) {
            // The player has a ring in the hotbar
            OneRingItem ringItem = (OneRingItem) playerItem.getItem();
            return ringItem.isActive();
        }

        return false;
    }
}
