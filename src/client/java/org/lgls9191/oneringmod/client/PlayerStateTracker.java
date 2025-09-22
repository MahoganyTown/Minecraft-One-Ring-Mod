package org.lgls9191.oneringmod.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.lgls9191.oneringmod.networking.UseRingS2CPayload;

import java.util.HashMap;
import java.util.Map;

public class PlayerStateTracker {
    public static final Map<Integer, Boolean> playersState = new HashMap<>();

    public static void initialize() {
        // Networking (handle packets sent from the server)
        ClientPlayNetworking.registerGlobalReceiver(UseRingS2CPayload.ID, (payload, context) -> {
            ClientWorld world = context.client().world;

            if (world == null) {
                return;
            }

            MinecraftClient mc = MinecraftClient.getInstance();

            if (mc.player != null) {
                if (context.player().getId() == mc.player.getId()) {
                    // The player who used the ring receives the packet
                    System.out.println("WE RECEIVED MOTHERFUCKER");
//                    mc.world.playSound();

                } else {
                    // The others players receive the packet

                }

//                PlayerStateTracker.updatePlayerState(context.player().getId(), payload.active());
            }
        });
    }

    public static void updatePlayerState(Integer playerID, Boolean newState) {
        playersState.put(playerID, newState);
    }
}
