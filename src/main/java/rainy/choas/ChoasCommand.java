package rainy.choas;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;

public class ChoasCommand {
    public static int start(CommandContext<ServerCommandSource> ctx) {
        ChoasTickManager.startGlobal();
        ctx.getSource().sendFeedback(() -> Text.literal(" The chaos event is now working brudther"), true);
        return 1;
    }
    public static int stop(CommandContext<ServerCommandSource> ctx) {
        ChoasTickManager.stopGlobal();
        ctx.getSource().sendFeedback(() -> Text.literal("Choas events is disabled sigh , we will miss you"), true);
        return 1;
    }
    public static int join(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only Players can use this command dude"));
            return 0;
        }
        ChoasTickManager.joinPlayer(player);
        ctx.getSource().sendFeedback(() -> Text.literal("§dYou joined The Game , Good Luck with surviving lmao."), true);
return 1;
    }
    public static int leave(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only players can  use this command"));
            return 0;
        }
        ChoasTickManager.leavePlayer(player);
        ctx.getSource().sendFeedback(() ->
                Text.literal("Scare lil baby? , Why you leaving huh"), true);
        return 1;
    }
}
