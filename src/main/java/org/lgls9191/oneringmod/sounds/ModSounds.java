package org.lgls9191.oneringmod.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static org.lgls9191.oneringmod.Oneringmod.MOD_ID;
import static org.lgls9191.oneringmod.Oneringmod.logger;

public class ModSounds {
    public static final SoundEvent WINDY_FIRE = registerSound("windy_fire");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void initialize() {
        logger.info("Registering mod sounds");
    }
}
