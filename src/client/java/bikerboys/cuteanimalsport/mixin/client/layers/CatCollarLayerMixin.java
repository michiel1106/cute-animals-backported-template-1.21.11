package bikerboys.cuteanimalsport.mixin.client.layers;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.cat.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.animal.feline.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(CatCollarLayer.class)
public abstract class CatCollarLayerMixin extends RenderLayer<CatRenderState, CatModel> {

    @Unique
    private static final Identifier CAT_BABY_COLLAR_LOCATION = Constants.of("textures/entity/cat/cat_collar_baby.png");

    public CatCollarLayerMixin(RenderLayerParent<CatRenderState, CatModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @WrapOperation(method = "<init>", at = @At(value = "NEW", target = "Lnet/minecraft/client/model/animal/feline/CatModel;", ordinal = 1))
    private static CatModel replaceBabyCatModel(ModelPart modelPart, Operation<CatModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabyCatModel(context.bakeLayer(ModModelLayers.BABY_CAT_COLLAR));
    }


    @WrapOperation(method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/CatRenderState;FF)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/CatCollarLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/Identifier;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V"))
    private void setCatBabyCollarLocation(Model model, Identifier identifier, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, LivingEntityRenderState state, int i1, int i2, Operation<Void> original) {
        if (state.isBaby) {
            original.call(model, CAT_BABY_COLLAR_LOCATION, poseStack, submitNodeCollector, i, state, i1, i2);
            return;
        }

        original.call(model, identifier, poseStack, submitNodeCollector, i, state, i1, i2);
    }


}
