package van.raen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import van.raen.fluids.CobFluids;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanVanExtras implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("vanvanextras");

	@Override
	public void onInitialize() {
		// Define an array of item identifiers
		String[] itemNames = { "incomplete_ore_item", "emerald_coin", "emerald_coin_stack", "diamond_coin",
				"diamond_coin_stack", "netherite_coin", "netherite_coin_stack", "crushed_raw_adamantite",
				"crushed_raw_aquarium", "crushed_raw_banglum", "crushed_raw_carmot", "crushed_raw_kyber",
				"crushed_raw_manganese", "crushed_raw_midas_gold", "crushed_raw_mythril", "crushed_raw_orichalcum",
				"crushed_raw_palladium", "crushed_raw_prometheum", "crushed_raw_quadrillum", "crushed_raw_runite",
				"crushed_raw_stormyx", "crushed_raw_thallasium", "crushed_cincinnasite", "copper_dust", "ball_mold",
				"copper_ball_frame", "iron_ball_frame", "gold_ball_frame", "diamond_ball_frame", "netherite_ball_frame",
				"red_apricorn_plate", "red_apricorn_ball_piece", "blue_apricorn_plate", "blue_apricorn_ball_piece",
				"yellow_apricorn_plate", "yellow_apricorn_ball_piece", "green_apricorn_plate",
				"green_apricorn_ball_piece", "pink_apricorn_plate", "pink_apricorn_ball_piece", "black_apricorn_plate",
				"black_apricorn_ball_piece", "white_apricorn_plate", "white_apricorn_ball_piece", "coin_mold",
				"blank_coin", "freshly_minted_coin" };

		// Iterate over the array and register each item
		for (String itemName : itemNames) {
			Item item = new Item(new FabricItemSettings());
			Registry.register(Registries.ITEM, new Identifier("vanvanextras", itemName), item);
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
				content.add(item);
			});
		}
		CobFluids.register();
	}
}