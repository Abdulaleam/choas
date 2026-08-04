package rainy.choas.events;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import rainy.choas.ChoasEventRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GibMeYourInV implements ChoasEventRegistry.ChoasEvent {
    private static final Random RANDOM = new Random();



    @Override
    public String execute(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server == null)
            return  player.getName().getString() + " §5§lHas no friends!";

        List<ServerPlayerEntity> others = server.getPlayerManager().getPlayerList().stream()
                .filter(p ->p != player)
                .collect(Collectors.toList());


        if (others.isEmpty()) {
            return player.getName().getString() + " §5§l Has No Frinds at all";
        }
        ServerPlayerEntity other = others.get(RANDOM.nextInt(others.size()));

        PlayerInventory playerInv = player.getInventory();
        PlayerInventory otherInv = other.getInventory();

        List<ItemStack> playerItems = new ArrayList<>();
        List<ItemStack> otherItems = new ArrayList<>();


        for (int i = 0; i < playerInv.size(); i++) playerItems.add(playerInv.getStack(i).copy());
        for (int i = 0; i <otherItems.size(); i++) otherItems.add(otherInv.getStack(i).copy());


         return player.getName().getString() + " §0§l and" + other.getName().getString() + " §0§lSwapped Inventorys!!";

    }
    public static void BoomGibme(ServerPlayerEntity player) {
        String message = new GibMeYourInV().execute(player);
        MinecraftServer server = player.getServer();
        if (server != null) {
            server.getPlayerManager().broadcast(Text.literal("§6[Chaos] §e" + message), false);
        }

        player.sendMessage(Text.literal("§d" + message), true);
    }
}
