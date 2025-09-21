package org.lgls9191.oneringmod;

import net.fabricmc.api.ModInitializer;
import org.lgls9191.oneringmod.items.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oneringmod implements ModInitializer {
    public static final String MOD_ID = "oneringmod";
    public static final Logger logger = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
    }
}
