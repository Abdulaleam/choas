package rainy.choas.events;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import rainy.choas.ChoasEventRegistry;

import java.util.Random;

public class MoneyRainBro implements ChoasEventRegistry.ChoasEvent {

    private static final Random RANDOM = new Random();

    private static final Item[] MONEY_ITEMS = {
            Items.DIAMOND , Items.ENCHANTED_GOLDEN_APPLE, Items.IRON_INGOT, Items.DIAMOND_BLOCK, Items.NETHERITE_BLOCK,
            Items.NETHERITE_INGOT, Items.DIAMOND_CHESTPLATE , Items.NETHERITE_SWORD
    };

    @Override
    public String execute(ServerPlayerEntity player) {
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            for (int i =0; i < 10; i++) {
                double x = player.getX() + RANDOM.nextInt(6) - 3;
                double z = player.getZ() + RANDOM.nextInt(6) - 3;
                Item item = MONEY_ITEMS[RANDOM.nextInt(MONEY_ITEMS.length)];
                ItemEntity itemEntity = new ItemEntity(serverWorld, x, player.getY() + 5 , z, new ItemStack(item));
                serverWorld.spawnEntity(itemEntity);
            }


        }
              return " §8§l IT'S RAINING MONEY HOLYYYY ON " + player.getName().getString() + "§8§l!!!";
    }
    public static void BoomMoney(ServerPlayerEntity player) {
        String message = new MoneyRainBro().execute(player);
        MinecraftServer server = player.getServer();
        if (server != null) {
            server.getPlayerManager().broadcast(Text.literal("§6[Chaos] §e" + message), false);
        }

        player.sendMessage(Text.literal("§d" + message), true);

    }

}
