package van.raen.fluids;

import java.util.*;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// Delete for Server-Jar
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class CobFluids {
        public static String modID = "vanvanextras";
        public static Map<String, FlowableFluid> STILL_FLUIDS = new HashMap<>();
        public static Map<String, FlowableFluid> FLOWING_FLUIDS = new HashMap<>();
        public static Map<String, Block> FLUID_BLOCKS = new HashMap<>();
        public static Map<String, Item> FLUID_BUCKETS = new HashMap<>();

        public static void register() {
                Object[][] cobFluids = {
                                // Minecraft
                                { "molten_iron", 0xfeeb6b, false },
                                { "molten_copper", 0xf2dcb5, false },
                                { "molten_gold", 0xfffd82, false },
                                { "molten_lapis", 0x24428a, false },
                                // Better Nether
                                { "molten_cincinnasite", 0xd1be65, false },
                                // Better End
                                { "molten_thallasium", 0x87dad2, false },
                                // Spectrum
                                { "molten_shimmerstone", 0xfcc621, false },
                                { "molten_azurite", 0x7ccdfc, false },
                                { "molten_stratine", 0xe44934, false },
                                { "molten_paltaeria", 0x50fcd0, false },
                                { "molten_malachite", 0x09ea98, false },
                                { "white_paint", 0xcacddb, true },
                                { "white_pigment", 0xebeef7, true },
                                { "light_gray_paint", 0x988d86, true },
                                { "light_gray_pigment", 0xc0b5ad, true },
                                { "gray_paint", 0x6a6671, true },
                                { "gray_pigment", 0x87808d, true },
                                { "black_paint", 0x404040, true },
                                { "black_pigment", 0x5a595c, true },
                                { "brown_paint", 0x8f5416, true },
                                { "brown_pigment", 0xcc8330, true },
                                { "red_paint", 0xc74420, true },
                                { "red_pigment", 0xfc6c28, true },
                                { "orange_paint", 0xfb9a14, true },
                                { "orange_pigment", 0xfcd849, true },
                                { "yellow_paint", 0xf5bd3b, true },
                                { "yellow_pigment", 0xfcfc41, true },
                                { "lime_paint", 0x7ac120, true },
                                { "lime_pigment", 0xb7fc37, true },
                                { "green_paint", 0x627d0c, true },
                                { "green_pigment", 0x93ae0c, true },
                                { "cyan_paint", 0x19b1e3, true },
                                { "cyan_pigment", 0x5afbea, true },
                                { "light_blue_paint", 0xaec4fc, true },
                                { "light_blue_pigment", 0xaec4fc, true },
                                { "blue_paint", 0x436ccc, true },
                                { "blue_pigment", 0x5da2fc, true },
                                { "purple_paint", 0xae53fc, true },
                                { "purple_pigment", 0xf374fc, true },
                                { "magenta_paint", 0xd240ed, true },
                                { "magenta_pigment", 0xfa8dfc, true },
                                { "pink_paint", 0xfc6f7d, true },
                                { "pink_pigment", 0xfcb1b3, true },
                                // Create
                                { "molten_zinc", 0x959892, false },
                                // Mythic Metals
                                { "molten_adamantite", 0xc40a23, false },
                                { "molten_aquarium", 0x66b4fc, false },
                                { "molten_banglum", 0x7d451c, false },
                                { "molten_carmot", 0xc1273f, false },
                                { "molten_kyber", 0x8a569f, false },
                                { "molten_manganese", 0xf59095, false },
                                { "molten_midas_gold", 0xfcef61, false },
                                { "molten_mythril", 0x63e7f8, false },
                                { "molten_orichalcum", 0x69b362, false },
                                { "molten_osmium", 0x8699b0, false },
                                { "molten_palladium", 0xed9926, false },
                                { "molten_platinum", 0xd5b6fc, false },
                                { "molten_prometheum", 0x52a76a, false },
                                { "molten_quadrillum", 0x307474, false },
                                { "molten_runite", 0x00aecf, false },
                                { "molten_silver", 0xafbbfc, false },
                                { "molten_stormyx", 0x90a3e9, false },
                                { "molten_tin", 0xf3d8dc, false },
                                // Cobblemon
                                { "medicinal_brew", 0x2DBAA0, true },
                                { "potion", 0x6F509D, true },
                                { "super_potion", 0xB95952, true },
                                { "hyper_potion", 0xDB589C, true },
                                { "max_potion", 0x2077BE, true },
                                { "full_restore", 0x61A835, true },
                                { "antidote", 0xF7D650, true },
                                { "awakening", 0x90F7FC, true },
                                { "burn_heal", 0x95FCB6, true },
                                { "ice_heal", 0xFCCECA, true },
                                { "paralyze_heal", 0xEAFC79, true },
                                { "full_heal", 0xD7CF1E, true },
                                { "ether", 0xD5A6DD, true },
                                { "max_ether", 0xB7ee95, true },
                                { "elixir", 0xEEAE95, true },
                                { "max_elixir", 0x95EEE1, true }
                };

                for (Object[] cobFluid : cobFluids) {
                        registerCobFluid((String) cobFluid[0], (int) cobFluid[1], (boolean) cobFluid[2]);
                }
        }

        public static void registerCobFluid(String fluidName, Integer color, boolean base) {
                STILL_FLUIDS.put(fluidName, Registry.register(Registries.FLUID,
                                new Identifier(modID, fluidName),
                                new FluidDefinition.Still(fluidName)));
                FLOWING_FLUIDS.put(fluidName, Registry.register(Registries.FLUID,
                                new Identifier(modID, "flowing_" + fluidName),
                                new FluidDefinition.Flowing(fluidName)));
                FLUID_BLOCKS.put(fluidName, Registry.register(Registries.BLOCK,
                                new Identifier(modID, fluidName + "_block"),
                                new FluidBlock(STILL_FLUIDS.get(fluidName),
                                                FabricBlockSettings
                                                                .copyOf((base == true ? Blocks.WATER : Blocks.LAVA))) {
                                }));
                FLUID_BUCKETS.put(fluidName, Registry.register(Registries.ITEM,
                                new Identifier(modID, fluidName + "_bucket"),
                                new BucketItem(STILL_FLUIDS.get(fluidName),
                                                new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1))));

                // Delete for Server-Jar
                FluidRenderHandlerRegistry.INSTANCE.register(STILL_FLUIDS.get(fluidName),
                                FLOWING_FLUIDS.get(fluidName),
                                new SimpleFluidRenderHandler(
                                                new Identifier(modID + ":block/" + fluidName + "_still"),
                                                new Identifier(modID + ":block/" + fluidName + "_flowing"),
                                                color));

                // Delete for Server-Jar
                BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                                STILL_FLUIDS.get(fluidName),
                                FLOWING_FLUIDS.get(fluidName));

                ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->

                {
                        for (Item item : FLUID_BUCKETS.values()) {
                                entries.add(item);
                        }
                });

        }
}