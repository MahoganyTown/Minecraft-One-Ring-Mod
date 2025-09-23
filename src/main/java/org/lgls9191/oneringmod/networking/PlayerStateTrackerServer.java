package org.lgls9191.oneringmod.networking;

import java.util.HashMap;
import java.util.Map;

public class PlayerStateTrackerServer {
    public static final Map<String, Boolean> playersState = new HashMap<>();

    public static void updatePlayerState(String playerUUID, Boolean newState) {
        playersState.put(playerUUID, newState);
    }
}
