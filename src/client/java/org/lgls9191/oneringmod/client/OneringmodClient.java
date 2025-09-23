package org.lgls9191.oneringmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import org.lgls9191.oneringmod.sounds.ModSounds;


public class OneringmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerStateTracker.initialize();
    }
}
