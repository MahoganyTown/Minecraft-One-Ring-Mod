package org.lgls9191.oneringmod.items;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.lgls9191.oneringmod.networking.UseRingS2CPayload;

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

            // Play loop sound
//            user.playSound(ModSounds.WINDY_FIRE, 1.0F, 1.0F);
//            world.playSoundFromEntityClient(user, ModSounds.WINDY_FIRE, SoundCategory.AMBIENT, 0.5F, 0.7F);
//            var soundManager = MinecraftClient.getInstance().getSoundManager();
        } else {
            // Send packet to server
            UseRingS2CPayload payload = new UseRingS2CPayload(active);

            for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
                ServerPlayNetworking.send(player, payload);
            }
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
