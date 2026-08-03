package rainy.choas;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import rainy.choas.events.RandomTeleport;

import java.util.List;
import java.util.Random;

public class ChoasEventRegistry {

    public interface ChoasEvent {
        String execute(ServerPlayerEntity player);

    }

    private static final Random RANDOM = new Random();



    private static final List<ChoasEvent> EVENTS = List.of(
            new RandomTeleport()

            // add the events i will make here later on

    );

    public static void BoomRandomEvents(ServerPlayerEntity player) {
        ChoasEvent event = EVENTS.get(RANDOM.nextInt(EVENTS.size()));
        String message = event.execute(player);

        MinecraftServer server = player.getServer();
        if (server != null) {
            server.getPlayerManager().broadcast(Text.literal("§6[Chaos] §e" + message), false);
        }

        player.sendMessage(Text.literal("§d" + message), true);

    }
}
