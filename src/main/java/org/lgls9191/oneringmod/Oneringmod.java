package org.lgls9191.oneringmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.lgls9191.oneringmod.items.ModItems;
import org.lgls9191.oneringmod.networking.UseRingS2CPayload;
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
    }
}
