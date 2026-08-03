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
            for (int i = 0; i < 3; i++) {
                ZombieEntity zombie = EntityType.ZOMBIE.create(serverWorld);

                if (zombie != null) {
                    double x = player.getX() + RANDOM.nextInt(6) - 3;
                    double z = player.getZ() + RANDOM.nextInt(6) - 3;

                    zombie.refreshPositionAndAngles(x, player.getY(), z, 0f, 0f);
                }
            }
        }
        return player.getName().getString() + " §l§9Got Jumped by Zombies , Lmao";
    }
}
