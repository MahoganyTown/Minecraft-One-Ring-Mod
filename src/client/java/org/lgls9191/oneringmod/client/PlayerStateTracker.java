package org.lgls9191.oneringmod.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.lgls9191.oneringmod.networking.UseRingS2CPayload;
import org.lgls9191.oneringmod.sounds.ModSounds;

import java.util.HashMap;
import java.util.Map;

public class PlayerStateTracker {
    public static final Map<String, Boolean> playersState = new HashMap<>();
    public static SoundInstance soundInstance;

    public static void initialize() {
        // Networking (handle packets sent from the server)
        ClientPlayNetworking.registerGlobalReceiver(UseRingS2CPayload.ID, (payload, context) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            ClientWorld world = context.client().world;

            if (world == null) {
                return;
            }

            if (mc.player != null) {
                if (payload.playerUUID().equals(mc.player.getUuidAsString())) {
                    // The player who used the ring receives the packet
                    MinecraftClient client = MinecraftClient.getInstance();

                    if (payload.active()) {
                        // Set ambien noise
                        soundInstance = PositionedSoundInstance.master(ModSounds.WINDY_FIRE, 1.0F);
                        client.getSoundManager().play(soundInstance);

                        // Set darkness status
                        StatusEffectInstance darknessEffect = new StatusEffectInstance(StatusEffects.DARKNESS, Integer.MAX_VALUE, 0, false, false);

                        if (client.player != null)
                            client.player.setStatusEffect(darknessEffect, null);
                    } else {
                        // Remove sound and visual effect
                        client.getSoundManager().stop(soundInstance);
                        if (client.player != null)
                            client.player.removeStatusEffect(StatusEffects.DARKNESS);
                    }
                } else {
                    // The others players receive the packet
                }

                PlayerStateTracker.updatePlayerState(payload.playerUUID(), payload.active());
            }
        });
    }

    public static void updatePlayerState(String playerUUID, Boolean newState) {
        playersState.put(playerUUID, newState);
    }
}
