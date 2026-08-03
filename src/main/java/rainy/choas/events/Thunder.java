package rainy.choas.events;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import rainy.choas.ChoasEventRegistry;

public class Thunder implements ChoasEventRegistry.ChoasEvent {

    @Override
    public String execute(ServerPlayerEntity player) {
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.setWeather(0, 600, true, true);
        }
        return player.getName().getString() + " §9§l Bro summoned a thunderstorm , he 's a mage?";
    }
}
