package rainy.choas.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rainy.choas.Choas;

import javax.security.auth.login.CredentialException;

public class RainyItems {

    public static final Item ARMOR = registerItem("armor", new ArmorItem(new Item.Settings().maxCount(1)));
    public static final Item SWAP = registerItem("swap", new SwapItem(new Item.Settings().maxCount(1)));
    public static final Item GIB = registerItem("gib", new GibInvItem(new Item.Settings().maxCount(1)));
    public static final Item COME = registerItem("come", new ComeHereItem(new Item.Settings().maxCount(1)));
    public static final Item Drop = registerItem("drop", new DropAllItem(new Item.Settings().maxCount(1)));
    public static final Item YOUME = registerItem("youme", new YouMeItem(new Item.Settings().maxCount(1)));
    public static final Item SHUFFLE = registerItem("shuffle", new InvShuffleItem(new Item.Settings().maxCount(1)));
    public static final Item HORSE =  registerItem("horse", new MyHorseItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Choas.MOD_ID, name), item);
    }


    public static void registerRainyItems() {}


}
