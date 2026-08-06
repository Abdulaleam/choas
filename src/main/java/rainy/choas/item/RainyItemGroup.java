package rainy.choas.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import rainy.choas.Choas;

import javax.swing.plaf.PanelUI;

public class RainyItemGroup {

    public static final ItemGroup MOMMY = Registry.register(Registries.ITEM_GROUP,
    Identifier.of(Choas.MOD_ID, "mommy"),
            FabricItemGroup.builder().icon(() -> new ItemStack(RainyItems.Drop))
                    .displayName(Text.translatable("itemgroup.choas.mommy"))
                    .entries((displayContext, entries) -> {
                        entries.add(RainyItems.ARMOR);
                        entries.add(RainyItems.Drop);
                        entries.add(RainyItems.COME);
                        entries.add(RainyItems.GIB);
                        entries.add(RainyItems.HORSE);
                        entries.add(RainyItems.SWAP);
                        entries.add(RainyItems.YOUME);
                        entries.add(RainyItems.SHUFFLE);
                        entries.add(RainyItems.TELEPORT);





                    })

                    .build());



    public static void RegisterRainyItemGroups(){}
}
