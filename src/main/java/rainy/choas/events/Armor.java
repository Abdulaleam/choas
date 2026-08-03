package rainy.choas.events;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import rainy.choas.ChoasEventRegistry;

import java.util.Random;

public class Armor implements ChoasEventRegistry.ChoasEvent {

    private static final Random RANDOM = new Random();

    private static final Item[] HELMETS = {
            Items.LEATHER_CHESTPLATE, Items.LEATHER_BOOTS, Items.IRON_BARS, Items.JACK_O_LANTERN, Items.GOLDEN_APPLE,
            Items.NETHERITE_BLOCK, Items.NETHERITE_LEGGINGS
    };
    @Override
    public String execute(ServerPlayerEntity player) {
        Item helmet = HELMETS[RANDOM.nextInt(HELMETS.length)];
        player.equipStack(EquipmentSlot.HEAD, new ItemStack(helmet));
        return player.getName().getString() + " §6§lummm what the f are you wearing up there.....";
    }
}
