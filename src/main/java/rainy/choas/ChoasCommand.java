package rainy.choas;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ChoasCommand {
    public static int start(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only Players can use this Command"));
            return 0;
        }
        ChoasTickManager.stopFor(player);
        ctx.getSource().sendFeedback(() -> Text.literal("§c§lChaos events started! Anything can happen every 30 seconds....!!!"), true);

         return 1;
    }
      public static int stop(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only Players can use this command,"));
            return 0;
        }
        ChoasTickManager.stopFor(player);
        ctx.getSource().sendFeedback(() -> Text.literal("§aChaos events stopped."), true);
        return 1;
      }
}
