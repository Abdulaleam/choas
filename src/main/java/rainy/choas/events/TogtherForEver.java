package rainy.choas.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.core.jmx.Server;
import rainy.choas.ChoasEventRegistry;

import java.util.*;
import java.util.stream.Collectors;

public class TogtherForEver implements ChoasEventRegistry.ChoasEvent{
    private static final Random RANDOM = new Random();

    private static final double MAX_DISTANCE = 6;
    private static final int LINK_DURATION_TICKS = 30 * 20;

    private static final Map<UUID, UUID> Togther = new HashMap<>();

    private static final Map<UUID, Integer> Timers = new HashMap<>();

    static {
        ServerTickEvents.END_SERVER_TICK.register(TogtherForEver::tickLinks);
    }

    @Override
    public String execute(ServerPlayerEntity player) {

        MinecraftServer server = player.getServer();

        if (server == null)
            return player.getName().getString() + " §6§lTried To Pair With a Friend , But No One IS ARound!";

        List<ServerPlayerEntity> friends = server.getPlayerManager().getPlayerList().stream()
                .filter(p ->p != player)
                .collect(Collectors.toList());
        if (friends.isEmpty()) {
            return player.getName().getString() + " §6§lTried To Pair With a Friend , But No One IS ARound!";

        }
        ServerPlayerEntity partner = friends.get(RANDOM.nextInt(friends.size()));


        Togther.put(player.getUuid(), partner.getUuid());
        Togther.put(partner.getUuid(),  player.getUuid());
        Timers.put(player.getUuid(), LINK_DURATION_TICKS);
        Timers.put(partner.getUuid(), LINK_DURATION_TICKS);

        return player.getName().getString() + " §6§land " + partner.getName().getString() + " §6§ Are Now Togther FOR EVER!!!!, well 30 seconds kinda not forver";

    }
    private static void tickLinks(MinecraftServer server) {
        if (Togther.isEmpty())
            return;

        Set<UUID> processed = new HashSet<>();

        Iterator<Map.Entry<UUID, UUID>> iterator = Togther.entrySet().iterator();


        while (iterator.hasNext()) {
            Map.Entry<UUID, UUID> entry = iterator.next();

            UUID uuid1 = entry.getKey();
            UUID uuid2 = entry.getValue();
            int TimeLeft = Timers.getOrDefault(uuid1, 0) - 1;

            ServerPlayerEntity player1 = server.getPlayerManager().getPlayer(uuid1);
            ServerPlayerEntity player2 = server.getPlayerManager().getPlayer(uuid2);


             if ( TimeLeft <= 0 || player1 == null || player2 == null ) {
                 Togther.remove(uuid1);
                 Togther.remove(uuid2);
                 Timers.remove(uuid1);
                 Timers.remove(uuid2);
                 continue;
             }
              Timers.put(uuid1, TimeLeft);
             Timers.put(uuid2, TimeLeft);

             double distance = player1.getPos().distanceTo(player2.getPos());
             if (distance > MAX_DISTANCE) {
                 Vec3d targetPos = player1.getPos();
                     player2.teleport(player1.getServerWorld(), targetPos.x, targetPos.y, targetPos.z, Set.of(),
                             player2.getYaw(), player2.getPitch());
             }
        }
    }
}
