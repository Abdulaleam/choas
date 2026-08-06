package rainy.choas.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import rainy.choas.events.MonsterWave;

public class MonsterItem extends Item {
    public MonsterItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult <net.minecraft.item.ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            String message = new MonsterWave().execute(serverPlayer);
            MonsterWave.BoomMonsterWave(serverPlayer);
        }
        return TypedActionResult.success(player.getStackInHand(hand), world.isClient());
    }
}
