package rainy.choas.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import rainy.choas.events.Iam_You_You_are_Me;

public class YouMeItem extends Item {
    public YouMeItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<net.minecraft.item.ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            String message = new Iam_You_You_are_Me().execute(serverPlayer);
            Iam_You_You_are_Me.BoomYouMe(serverPlayer);
        }
        return TypedActionResult.success(player.getStackInHand(hand), world.isClient());
    }
}
