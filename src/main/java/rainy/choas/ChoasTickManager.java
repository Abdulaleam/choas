package rainy.choas;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.*;

public class ChoasTickManager {
    private static final int INTERVAL_TICKS = 20 * 30; // 30 seconds should be fine ehhhh ima leave it

    private static boolean running = false;
    private static final Set<UUID> joinedPlayers = new HashSet<>();
    private static final Map<UUID, Integer> tickCounters = new HashMap<>();

    public static void startGlobal(){
        running = true;
    }
    public static void stopGlobal() {
        running = false;
    }
    public static void joinPlayer(ServerPlayerEntity player) {
        joinedPlayers.add(player.getUuid());
        tickCounters.put(player.getUuid(), INTERVAL_TICKS);
    }
    public static void leavePlayer(ServerPlayerEntity player) {
        joinedPlayers.remove(player.getUuid());
        tickCounters.remove(player.getUuid());
    }
    public static void onServerTick(MinecraftServer server) {
        if (!running || joinedPlayers.isEmpty())
            return;

        Iterator<UUID> iterator = joinedPlayers.iterator();
        while (iterator.hasNext()) {
            UUID uuid = iterator.next();
            ServerPlayerEntity player = server.getPlayerManager().getPlayer(uuid);

            if (player ==null) {
                iterator.remove();
                tickCounters.remove(uuid);
                continue;
            }
            int ticksLeft = tickCounters.getOrDefault(uuid, INTERVAL_TICKS) -1;
            if (ticksLeft <= 0) {
                ChoasEventRegistry.BoomRandomEvents(player);
                tickCounters.put(uuid, INTERVAL_TICKS);
            } else {
                tickCounters.put(uuid, ticksLeft);

            }
        }
    }

}