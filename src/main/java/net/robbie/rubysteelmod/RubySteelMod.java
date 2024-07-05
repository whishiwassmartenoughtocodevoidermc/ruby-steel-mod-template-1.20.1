package net.robbie.rubysteelmod;
import net.minecraft.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.robbie.rubysteelmod.block.modblock;
import net.robbie.rubysteelmod.item.custom.RubyArrowItem;
import net.robbie.rubysteelmod.item.custom.RubyBowItem;
import net.robbie.rubysteelmod.item.moditem;
import net.robbie.rubysteelmod.item.moditemgroups;
import net.robbie.rubysteelmod.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RubySteelMod implements ModInitializer {
    public static final String MOD_ID = "rubysteelmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        moditemgroups.registerItemGroups();
        moditem.registermoditem();
        modblock.registerModBlocks();
        ModWorldGeneration.generateModWorldGen();

        // Register Ruby Bow Item
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ruby_bow"), new RubyBowItem(new FabricItemSettings().fireproof()));

        // Register Ruby Arrow Item
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ruby_arrow"), new RubyArrowItem(new FabricItemSettings()));

        // Optionally, perform any additional setup with your items here
    }
}
