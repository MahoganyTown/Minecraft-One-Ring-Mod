package org.lgls9191.oneringmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.lgls9191.oneringmod.items.ModItems;
import org.lgls9191.oneringmod.networking.PlayerStateTrackerServer;
import org.lgls9191.oneringmod.networking.UseRingS2CPayload;
import org.lgls9191.oneringmod.payload.PlayerUtils;
import org.lgls9191.oneringmod.sounds.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oneringmod implements ModInitializer {
    public static final String MOD_ID = "oneringmod";
    public static final Logger logger = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModSounds.initialize();

        // Networking
        PayloadTypeRegistry.playS2C()
                .register(UseRingS2CPayload.ID, UseRingS2CPayload.CODEC);

        // Register the connection event
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.player;
            PlayerUtils.deacAllPlayerRings(player.getInventory());
            deacRingPlayer(server, player.getUuidAsString());
        });

        // Register the disconnection event
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerPlayerEntity player = handler.player;
            PlayerUtils.deacAllPlayerRings(player.getInventory());
            deacRingPlayer(server, player.getUuidAsString());
        });
    }

    public static void deacRingPlayer(MinecraftServer server, String uuid) {
        // Update server-side player tracker
        PlayerStateTrackerServer.updatePlayerState(uuid, false);

        // Send packet to server
        UseRingS2CPayload payload = new UseRingS2CPayload(uuid, false);

        if (server != null) {
            PlayerLookup.all(server).forEach(player -> ServerPlayNetworking.send(player, payload));
        }
    }
}
