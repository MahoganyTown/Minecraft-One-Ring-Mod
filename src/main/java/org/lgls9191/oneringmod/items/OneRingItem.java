package org.lgls9191.oneringmod.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class OneRingItem extends Item {
    // Is the ring on or not
    private boolean active = false;

    public OneRingItem(Settings settings) {
        super(settings);
    }

    // write this if the version is 1.21.2 or higher:
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            // Toggle activate or deactivate
            toggleActive();

            // Play entry sound
            user.playSound(SoundEvents.EVENT_MOB_EFFECT_BAD_OMEN, 1.0F, 1.0F);
        }

        return ActionResult.SUCCESS;
    }

    public void toggleActive() {
        active = !active;
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }
}
