package rainy.choas.events;

import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import rainy.choas.ChoasEventRegistry;

public class DropAll implements ChoasEventRegistry.ChoasEvent {

    @Override
    public String execute(ServerPlayerEntity player) {
        var inventory = player.getInventory();

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);

            if (!stack.isEmpty()) {
                player.dropItem(stack.copy(), true, false);
                inventory.setStack(i, ItemStack.EMPTY);
            }
        }
        return player.getName().getString() + " §1§lGot Robbed by the IRS " ;
    }
    public static void BoomDropAll(ServerPlayerEntity player) {
        String message = new DropAll().execute(player);
        MinecraftServer server = player.getServer();
        if (server != null) {
            server.getPlayerManager().broadcast(Text.literal("§6[Chaos] §e" + message), false);
        }

        player.sendMessage(Text.literal("§d" + message), true);
    }
}
