package net.robbie.rubysteelmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.robbie.rubysteelmod.item.moditem;

import java.util.concurrent.CompletableFuture;

public class  ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> copletableFuture){
   super(output,copletableFuture);

}

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
    getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
            .add(moditem.RUBY_HELMET,moditem.RUBY_CHESTPLATE,moditem.RUBY_LEGGINGS,moditem.RUBY_BOOTS);
    getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
            .add(moditem.STEEL_HELMET,moditem.STEEL_CHESTPLATE,moditem.STEEL_LEGGINGS,moditem.STEEL_BOOTS);

    }
}
