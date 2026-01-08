package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(OcelotRenderer.class)
public class OcelotRendererMixin {
    @Shadow
    @Final
    private static Identifier CAT_OCELOT_LOCATION;
    @Unique
    private static final Identifier CUSTOM_CAT_OCELOT_BABY_LOCATION = Constants.of("textures/entity/cat/ocelot_baby.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/FelineRenderState;)Lnet/minecraft/resources/Identifier;", at = @At("HEAD"),cancellable = true)
    private void setNewOcelotBabyTexture(FelineRenderState state, CallbackInfoReturnable<Identifier> cir) {
        cir.setReturnValue(state.isBaby ? CUSTOM_CAT_OCELOT_BABY_LOCATION : CAT_OCELOT_LOCATION);
        cir.cancel();
    }


    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;bakeLayer(Lnet/minecraft/client/model/geom/ModelLayerLocation;)Lnet/minecraft/client/model/geom/ModelPart;", ordinal = 1))
    private static ModelPart setNewOcelotModel(EntityRendererProvider.Context instance, ModelLayerLocation modelLayerLocation, Operation<ModelPart> original) {
        return instance.bakeLayer(ModModelLayers.BABY_OCELOT);
    }


}
