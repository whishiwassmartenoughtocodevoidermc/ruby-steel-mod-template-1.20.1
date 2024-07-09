package net.robbie.rubysteelmod.mixin;

import net.robbie.rubysteelmod.RubySteelMod;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void addModel(ModelIdentifier modelId);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void addRubyItems(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates, CallbackInfo ci) {
        this.addModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_sword_3d", "inventory"));
        this.addModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_axe_3d", "inventory"));
        this.addModel(new ModelIdentifier(RubySteelMod.MOD_ID, "redsteel_katana_3d", "inventory"));
        this.addModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_bow_3d", "inventory"));
        this.addModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_arrow_3d", "inventory"));
        this.addModel(new ModelIdentifier(RubySteelMod.MOD_ID, "ruby_pickaxe_3d", "inventory"));
    }
}
