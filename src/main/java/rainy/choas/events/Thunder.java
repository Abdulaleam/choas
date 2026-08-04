package rainy.choas.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import rainy.choas.ChoasEventRegistry;

public class Thunder implements ChoasEventRegistry.ChoasEvent {

    @Override
    public String execute(ServerPlayerEntity player) {
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.setWeather(0, 600, true, true);
        }
        return player.getName().getString() + " §9§l Bro summoned a thunderstorm , he 's a mage?";
    }


    public static void BoomThunderEvent(ServerPlayerEntity player) {
        String message = new Thunder().execute(player);

        MinecraftServer server = player.getServer();
        if (server != null) {
            server.getPlayerManager().broadcast(Text.literal("§6[Chaos] §e" + message), false);
        }

        player.sendMessage(Text.literal("§d" + message), true);
        }
    }

