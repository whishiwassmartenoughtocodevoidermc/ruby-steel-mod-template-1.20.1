package net.robbie.rubysteelmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.robbie.rubysteelmod.RubySteelMod;
import net.robbie.rubysteelmod.block.modblock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class moditemgroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(RubySteelMod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(moditem.RUBY)).entries((displayContext, entries) -> {
                        entries.add(moditem.RUBY);
                        entries.add(moditem.RAW_RUBY);



                        entries.add(modblock.RUBY_BLOCK);
                        entries.add(modblock.RAW_RUBY_BLOCK);


                    }).build());


    public static void registerItemGroups() {
        RubySteelMod.LOGGER.info("Registering Item Groups for " + RubySteelMod.MOD_ID);
    }
}