package net.robbie.rubysteelmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.ItemGroups;
import net.robbie.rubysteelmod.RubySteelMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.robbie.rubysteelmod.item.moditem;

public class modblock {
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RAW_RUBY_BLOCK = registerBlock("raw_ruby_block.json",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(RubySteelMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(RubySteelMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static void addItemstoingrediantstab(FabricItemGroupEntries entries) {
        entries.add(RUBY_BLOCK);
        entries.add(RAW_RUBY_BLOCK);
    }

    public static void registerModBlocks() {
        RubySteelMod.LOGGER.info("Registering ModBlocks for " + RubySteelMod.MOD_ID);
    }

    static {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(modblock::addItemstoingrediantstab);
    }
}
