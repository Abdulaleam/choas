package rainy.choas.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import rainy.choas.ChoasEventRegistry;
import rainy.choas.events.Armor;

public class ArmorItem extends Item {
    public ArmorItem(Settings settings) {
        super(settings);
    }

    @Override

    public TypedActionResult<net.minecraft.item.ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            String message = new Armor().execute(serverPlayer);
            Armor.BoomArmorEvent(serverPlayer);
        }
        return TypedActionResult.success(player.getStackInHand(hand), world.isClient());
    }
}
