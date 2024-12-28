package net.robbie.rubysteelmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.robbie.rubysteelmod.item.moditem;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {


        itemModelGenerator.registerArmor(((ArmorItem) moditem.RUBY_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) moditem.RUBY_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) moditem.RUBY_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) moditem.RUBY_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) moditem.STEEL_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) moditem.STEEL_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) moditem.STEEL_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) moditem.STEEL_BOOTS));

    }
}