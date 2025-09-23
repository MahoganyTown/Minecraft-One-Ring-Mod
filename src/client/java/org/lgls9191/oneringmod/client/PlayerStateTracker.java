package org.lgls9191.oneringmod.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.lgls9191.oneringmod.networking.UseRingS2CPayload;

import java.util.HashMap;
import java.util.Map;

public class PlayerStateTracker {
    public static final Map<String, Boolean> playersState = new HashMap<>();

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
//                    mc.world.playSound();
//                    System.out.println("I, " + mc.player.ge);
                } else {
                    // The others players receive the packet
                }

                PlayerStateTracker.updatePlayerState(payload.playerUUID(), payload.active());

                for (HashMap.Entry<String, Boolean> entry : playersState.entrySet()) {
                    String key = entry.getKey();
                    Boolean value = entry.getValue();
                    System.out.println("Player: " + key + ", Invisible: " + value);
                }
            }
        });
    }

    public static void updatePlayerState(String playerUUID, Boolean newState) {
        playersState.put(playerUUID, newState);
    }
}
