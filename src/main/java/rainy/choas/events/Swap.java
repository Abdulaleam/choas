package rainy.choas.events;

import com.ibm.icu.impl.coll.UVector32;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import rainy.choas.ChoasEventRegistry;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.util.math.Vec3d;

public class Swap implements ChoasEventRegistry.ChoasEvent {


    private static final Random RANDOM = new Random();

    @Override
    public String execute(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server == null)
            return player.getName().getString() + " §l§4 Tried to Swap , But Remembered He Has No Friends";

        List<ServerPlayerEntity> others = server.getPlayerManager().getPlayerList().stream()
                .filter(p -> p != player)
                .collect(Collectors.toList());
        if (others.isEmpty()) {
            return player.getName().getString() + " §l§4 Tried to Swap , But Remembered He Has No Friends ";
        }
        ServerPlayerEntity other = others.get(RANDOM.nextInt(others.size()));


        Vec3d playerPos = player.getPos();
        Vec3d otherPos = other.getPos();

        player.teleport(player.getServerWorld(), otherPos.x, otherPos.y, otherPos.z, Set.of(), player.getYaw(), player.getPitch());
        other.teleport(other.getServerWorld(), playerPos.x, playerPos.y, playerPos.z, Set.of(), other.getYaw(), other.getPitch());

        return player.getName().getString() + " and " + other.getName().getString() + " §l§6 Swapped Places";
    }
}
