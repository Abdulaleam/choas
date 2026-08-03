package rainy.choas;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
public class ChoasCommand {
    public static int start(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only Players can use this command dude"));
            return 0;
        }
        if (!ChoasTickManager.isJoined(player)) {
            ctx.getSource().sendFeedback(() ->
                    Text.literal("You're not even in the game, /choas join first").formatted(Formatting.GRAY, Formatting.BOLD), true);
            return 0;
        }
        boolean started = ChoasTickManager.startGlobal();
        if (!started) {
            ctx.getSource().sendFeedback(() ->
                    Text.literal("The game already started bro").formatted(Formatting.GRAY, Formatting.BOLD), true);
            return 0;
        }
        ctx.getSource().sendFeedback(() ->
                Text.literal("The chaos event is now working brudther").formatted(Formatting.GREEN, Formatting.BOLD), true);
        return 1;
    }

    public static int stop(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only Players can use this command dude"));
            return 0;
        }
        boolean stopped = ChoasTickManager.stopGlobal();
        if (!stopped) {
            ctx.getSource().sendFeedback(() ->
                    Text.literal("The game isn't even running bro").formatted(Formatting.GRAY, Formatting.BOLD), true);
            return 0;
        }
        ctx.getSource().sendFeedback(() ->
                Text.literal("Choas events is disabled sigh , we will miss you").formatted(Formatting.RED, Formatting.BOLD), true);
        return 1;
    }

    public static int join(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only Players can use this command dude"));
            return 0;
        }
        boolean joined = ChoasTickManager.joinPlayer(player);
        if (!joined) {
            ctx.getSource().sendFeedback(() ->
                    Text.literal("You're already inside the game dude").formatted(Formatting.GRAY, Formatting.BOLD), true);
            return 0;
        }
        ctx.getSource().sendFeedback(() ->
                Text.literal("You joined The Game , Good Luck with surviving lmao.").formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD), true);
        return 1;
    }

    public static int leave(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity player = ctx.getSource().getPlayer();
        if (player == null) {
            ctx.getSource().sendError(Text.literal("Only players can  use this command"));
            return 0;
        }
        boolean left = ChoasTickManager.leavePlayer(player);
        if (!left) {
            ctx.getSource().sendFeedback(() ->
                    Text.literal(" §5You're not even in the game dude, wake up").formatted(Formatting.GRAY, Formatting.BOLD), true);
            return 0;
        }
        ctx.getSource().sendFeedback(() ->
                Text.literal(" §4Scared lil baby? , Why you leaving huh").formatted(Formatting.YELLOW, Formatting.BOLD), true);
        return 1;
    }


}