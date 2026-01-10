package bikerboys.cuteanimalsport.mixin.client.layers;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.animal.sheep.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.client.renderer.rendertype.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(SheepWoolLayer.class)
public class SheepWoolLayerMixin {
    @Unique
    private static final Identifier BABY_SHEEP_WOOL_LOCATION = Constants.of("textures/entity/sheep/sheep_wool_baby.png");



    @WrapOperation(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/SheepRenderState;FF)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/rendertype/RenderTypes;outline(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/rendertype/RenderType;"))
    private RenderType changeSheepThing(Identifier identifier, Operation<RenderType> original, @Local(argsOnly = true) SheepRenderState sheepRenderState) {
        if (sheepRenderState.isBaby) {
            return RenderTypes.outline(BABY_SHEEP_WOOL_LOCATION);
        }
        return original.call(identifier);
    }




    @WrapOperation(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/SheepRenderState;FF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/SheepWoolLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/Identifier;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V"))
    private void changeOtherSheepThing(Model model, Identifier identifier, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, LivingEntityRenderState state, int i1, int i2, Operation<Void> original) {

        if (state.isBaby) {
            original.call(model, BABY_SHEEP_WOOL_LOCATION, poseStack, submitNodeCollector, i , state, i1, i2);
            return;
        }


        original.call(model, identifier, poseStack, submitNodeCollector, i , state, i1, i2);
    }





    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/EntityModelSet;bakeLayer(Lnet/minecraft/client/model/geom/ModelLayerLocation;)Lnet/minecraft/client/model/geom/ModelPart;", ordinal = 1))
    private ModelPart changeWoolModel(EntityModelSet instance, ModelLayerLocation modelLayerLocation, Operation<ModelPart> original, @Local(argsOnly = true) RenderLayerParent<SheepRenderState, SheepModel> renderLayerParent) {
        return original.call(instance, ModModelLayers.BABY_SHEEP_WOOL);
    }



}
