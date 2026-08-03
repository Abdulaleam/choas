package rainy.choas.events;

import net.minecraft.entity.TntEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import rainy.choas.ChoasEventRegistry;

public class TnT implements ChoasEventRegistry.ChoasEvent {
    @Override

    public String execute(ServerPlayerEntity player) {
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            TntEntity tnt = new TntEntity(serverWorld, player.getX(), player.getY() +1, player.getZ(), null);
            tnt.setFuse(60);
            serverWorld.spawnEntity(tnt);
        }
        return player.getName().getString() + " §1§l 3 2 1... Look next to you gang, BOOM  RUN!!!";
    }
}
