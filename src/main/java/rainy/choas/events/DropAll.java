package rainy.choas.events;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
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
}
