package rainy.choas;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rainy.choas.item.RainyItemGroup;
import rainy.choas.item.RainyItems;

public class Choas implements ModInitializer {
	public static final String MOD_ID = "choas";

	@Override
	public void onInitialize() {
		RainyItems.registerRainyItems();
		RainyItemGroup.RegisterRainyItemGroups();
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) ->
				dispatcher.register(CommandManager.literal("choas")
						.then(CommandManager.literal("start").executes(ChoasCommand::start))
						.then(CommandManager.literal("stop").executes(ChoasCommand::stop))
						.then(CommandManager.literal("join").executes(ChoasCommand::join))
						.then(CommandManager.literal("leave").executes(ChoasCommand::leave))
				)));

		ServerTickEvents.END_SERVER_TICK.register(ChoasTickManager::onServerTick);

	}

}