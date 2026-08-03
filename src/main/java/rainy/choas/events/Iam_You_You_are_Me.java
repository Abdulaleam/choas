package rainy.choas.events;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import rainy.choas.ChoasEventRegistry;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Iam_You_You_are_Me implements ChoasEventRegistry.ChoasEvent {


    private static final Random RANDOM = new Random();


    @Override
    public String execute(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();

        if (server == null)
            return player.getName().getString() + " §8§lGET A FRIEND BRO ";
        List<ServerPlayerEntity> others = server.getPlayerManager().getPlayerList().stream()
                .filter(p -> p != player)
                .collect(Collectors.toList());

        if (others.isEmpty()) {
            return player.getName().getString() + " §8§lGET A FRIEND BRO ";
        }
         ServerPlayerEntity other = others.get(RANDOM.nextInt(others.size()));


        float playerHealth = player.getHealth();
        float otherHealth = other.getHealth();
        int playerHunger = player.getHungerManager().getFoodLevel();
        int otherHunger = other.getHungerManager().getFoodLevel();
        int playerExp = player.experienceLevel;
        int otherExp = other.experienceLevel;

        player.setHealth(Math.min(otherHealth, player.getMaxHealth()));
        other.setHealth(Math.min(playerHealth, other.getMaxHealth()));

        player.getHungerManager().setFoodLevel(otherHunger);
        other.getHungerManager().setFoodLevel(playerHunger);

        player.setExperienceLevel(otherExp);
        other.setExperienceLevel(playerExp);

        return player.getName().getString() + " §8§lIs Now" + other.getName().getString();

    }

}
