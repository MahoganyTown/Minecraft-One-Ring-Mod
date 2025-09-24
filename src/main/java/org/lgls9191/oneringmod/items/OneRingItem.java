package org.lgls9191.oneringmod.items;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.lgls9191.oneringmod.networking.PlayerStateTrackerServer;
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
            // Play entry sound
            user.playSound(SoundEvents.EVENT_MOB_EFFECT_BAD_OMEN, 1.0F, 1.0F);
        } else {
            // Toggle activate or deactivate
            toggleActive();

            // Update server-side player tracker
            PlayerStateTrackerServer.updatePlayerState(user.getUuidAsString(), active);

            // Send packet to server
            UseRingS2CPayload payload = new UseRingS2CPayload(user.getUuidAsString(), active);

            if (world.getServer() != null) {
                for (ServerPlayerEntity player : PlayerLookup.all(world.getServer())) {
                    ServerPlayNetworking.send(player, payload);
                }
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
