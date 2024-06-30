package net.robbie.rubysteelmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.robbie.rubysteelmod.RubySteelMod;

public class moditem {
    public static final Item REDSTEEL = registeritem("redsteel",new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registeritem("raw_ruby",new Item(new FabricItemSettings()));
    public static final Item RUBY = registeritem("ruby",new Item(new FabricItemSettings()));
    public static final Item TUNGSTEN = registeritem("tungsten",new Item(new FabricItemSettings()));
    public static final Item STEEL = registeritem("steel",new Item(new FabricItemSettings()));
    public static final Item RAW_TUNGSTEN = registeritem("raw_tungsten",new Item(new FabricItemSettings()));
    public static final Item RUBY_DUST = registeritem("ruby_dust",new Item(new FabricItemSettings()));
    public static final Item RUBY_SWORD = registeritem("ruby_sword",new SwordItem(modtoolmaterial.RUBY,4,-2.4f,new FabricItemSettings().fireproof()));
    public static final Item RUBY_PICKAXE = registeritem("ruby_pickaxe",new PickaxeItem((modtoolmaterial.RUBY),1,0.6f,new FabricItemSettings().fireproof()));
    public static final Item RUBY_AXE = registeritem("ruby_axe", new AxeItem((modtoolmaterial.RUBY), 6, -3.0f, new FabricItemSettings().fireproof()));





    private static void addItemstoingrediantstab(FabricItemGroupEntries entries) {
        entries.add(RUBY);
        entries.add(TUNGSTEN);
        entries.add(STEEL);
        entries.add(REDSTEEL);
        entries.add(RAW_RUBY);
        entries.add(RAW_TUNGSTEN);
        entries.add(RUBY_DUST);

    }

    private static void addItemstoweponstab(FabricItemGroupEntries entries) {
     entries.add(RUBY_SWORD);
     entries.add(RUBY_AXE);
    }

    private static void addItemstotoolstab(FabricItemGroupEntries entries) {
     entries.add(RUBY_PICKAXE);
    }

    private static Item registeritem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RubySteelMod.MOD_ID, name), item);
    }

    public static void registermoditem() {
        RubySteelMod.LOGGER.info("Registering mod items for  " + RubySteelMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(moditem::addItemstoingrediantstab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(moditem::addItemstoweponstab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(moditem::addItemstotoolstab);
    }
}



