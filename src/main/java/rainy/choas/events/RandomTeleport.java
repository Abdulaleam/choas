package rainy.choas.events;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import rainy.choas.ChoasEventRegistry;

import java.util.Random;
import java.util.Set;

public class RandomTeleport implements ChoasEventRegistry.ChoasEvent {
    private static final Random RANDOM = new Random();
    private static final int RADIUS = 75;

    @Override
    public String execute(ServerPlayerEntity player) {
        World world = player.getWorld();

        int x = player.getBlockX() + RANDOM.nextInt(RADIUS * 2) - RADIUS;
        int z = player.getBlockZ() + RANDOM.nextInt(RADIUS * 2) - RADIUS;
        int y = player.getBlockY() + RANDOM.nextInt(RADIUS * 2) - RADIUS;
        player.teleport(player.getServerWorld(), x + 0.5, y, z +0.5, Set.of(), player.getYaw(), player.getPitch());

        return player.getName().getString() + " §7§lBro Blinked and Found Himself Lost , Go Get Milk!";
    }
}
