package org.lgls9191.oneringmod.client;

import net.fabricmc.api.ClientModInitializer;


public class OneringmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerStateTracker.initialize();
    }
}
