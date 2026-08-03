package rainy.choas;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class ChoasTickManager {
    private static final int INTERVAL_TICKS = 20 * 30; // 30 seconds should be fine ehhhh ima leave it

    private static final Map<UUID, Integer> activtePlayers = new HashMap<>();

    public static void startFor(ServerPlayerEntity player) {
        activtePlayers.put(player.getUuid(), INTERVAL_TICKS);
    }

    public static void stopFor(ServerPlayerEntity player) {
        activtePlayers.remove(player.getUuid());
    }
    public static void onServerTick(MinecraftServer server) {
        if (activtePlayers.isEmpty())
            return;

        Iterator<Map.Entry<UUID, Integer>> iterator = activtePlayers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            ServerPlayerEntity player = server.getPlayerManager().getPlayer(entry.getKey());

            if (player ==null) {
                iterator.remove();
                continue;
            }
            int ticksLeft = entry.getValue() - 1;
            if (ticksLeft <= 0) {
                ChoasEventRegistry.BoomRandomEvents(player);
                entry.setValue(INTERVAL_TICKS);
            } else {
                entry.setValue(ticksLeft);
                
            }
        }
    }

}