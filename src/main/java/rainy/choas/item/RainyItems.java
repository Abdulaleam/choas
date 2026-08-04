package rainy.choas.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rainy.choas.Choas;

public class RainyItems {

    public static final Item ARMOR = registerItem("armor", new ArmorItem(new Item.Settings()));
    public static final Item SWAP = registerItem("swap", new SwapItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Choas.MOD_ID, name), item);
    }


    public static void registerRainyItems() {}


}
