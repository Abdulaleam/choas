package rainy.choas.events;

import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import rainy.choas.ChoasEventRegistry;

import java.util.*;

public class InventoryShuffle implements ChoasEventRegistry.ChoasEvent {

    private static final Random RANDOM = new Random();
    @Override
    public String execute(ServerPlayerEntity player) {
        var inventory = player.getInventory();

        List<ItemStack> stacks = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++) stacks.add(inventory.getStack(i));
        Collections.shuffle(stacks, RANDOM);

            for (int i = 0; i < inventory.size(); i++) inventory.setStack(i, stacks.get(i));


        return player.getName().getString() + " §2§l's inventory got shuffled!! Bozo , Well Get Better noob";


    }
    public static void BoomInventory(ServerPlayerEntity player) {
        String message = new InventoryShuffle().execute(player);

        MinecraftServer server = player.getServer();
        if (server != null){
            server.getPlayerManager().broadcast(Text.literal("§6[Chaos] §e" + message), false);
        }

        player.sendMessage(Text.literal("§d" + message), true);
    }
}
