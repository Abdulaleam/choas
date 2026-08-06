package rainy.choas.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import rainy.choas.ChoasEventRegistry;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ComeHere implements ChoasEventRegistry.ChoasEvent {
    private static final Random RANDOM = new Random();

    @Override
    public String execute(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();

          if( server == null)
              return player.getName().getString() + " §9§l Has No Friends :( , He 's a Lone Wolf!";

        List<ServerPlayerEntity> others = server.getPlayerManager().getPlayerList().stream()
                .filter(p -> p != player)
                .collect(Collectors.toList());
        if (others.isEmpty()) {
            return player.getName().getString() + "§9§l Has No Friends :( , He's a Lone Wolf!";
        }
        ServerPlayerEntity other = others.get(RANDOM.nextInt(others.size()));
        Vec3d pos = player.getPos();

        other.teleport(player.getServerWorld(), pos.x + 1, pos.y + 1 , pos.z + 1, Set.of(), other.getYaw(), other.getPitch());

        return other.getName().getString() + " §6§l Bro Got Yanked Next to " + player.getName().getString() + " §6§l!!! Lmaoo";

    }
    public static void BoomComeHere(ServerPlayerEntity player) {
        String message = new ComeHere().execute(player);
        MinecraftServer server = player.getServer();
        if (server != null) {
            server.getPlayerManager().broadcast(Text.literal("§6[Chaos] §e" + message), false);
        }

        player.sendMessage(Text.literal("§d" + message), true);
    }
}
