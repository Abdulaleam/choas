package rainy.choas.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import rainy.choas.ChoasEventRegistry;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MyHorse implements ChoasEventRegistry.ChoasEvent {

    private static final Random RANDOM = new Random();

    @Override

    public String execute(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server == null)
            return player.getName().getString() + " §3§l Tried to get a Ride , But He Forgot He Lives Alone! eee";

        List<ServerPlayerEntity> others = server.getPlayerManager().getPlayerList().stream()
                .filter(p ->p != player)
                .collect(Collectors.toList());
        if (others.isEmpty()) {
            return player.getName().getString() + "§3§l Tried to get a Ride , But He Forgot He Lives Alone! eee";

        }

        ServerPlayerEntity mount = others.get(RANDOM.nextInt(others.size()));
        player.startRiding(mount, true);

        return player.getName().getString() + " §6§lis now Mounting " + mount.getName().getString() + " §6§lLike a Horse ";

    }

}
