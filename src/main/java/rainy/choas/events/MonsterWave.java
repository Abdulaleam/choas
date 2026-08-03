package rainy.choas.events;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import rainy.choas.ChoasEventRegistry;

import java.util.Random;

public class MonsterWave implements ChoasEventRegistry.ChoasEvent {
    private static final Random RANDOM = new Random();

    @Override
    public String execute(ServerPlayerEntity player) {
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 10; i++) {
                ZombieEntity zombie = EntityType.ZOMBIE.create(serverWorld);

                if (zombie != null) {
                    double x = player.getX() + RANDOM.nextInt(10) - 5;
                    double z = player.getZ() + RANDOM.nextInt(10) - 5;

                    zombie.refreshPositionAndAngles(x, player.getY(), z, 0f, 0f);

                    serverWorld.spawnEntity(zombie);
                }
            }
        }
        return player.getName().getString() + " §l§9Got Jumped by Zombies , Lmao";
    }
}
