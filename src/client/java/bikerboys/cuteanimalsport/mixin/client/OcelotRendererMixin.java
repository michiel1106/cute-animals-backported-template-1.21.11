package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.ocelot.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Debug(export = true)
@Mixin(OcelotRenderer.class)
public class OcelotRendererMixin {
    @Shadow
    @Final
    private static ResourceLocation CAT_OCELOT_LOCATION;
    @Unique
    private static final ResourceLocation CUSTOM_CAT_OCELOT_BABY_LOCATION = Constants.of("textures/entity/cat/ocelot_baby.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/FelineRenderState;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"),cancellable = true)
    private void setNewOcelotBabyTexture(FelineRenderState state, CallbackInfoReturnable<ResourceLocation> cir) {
        cir.setReturnValue(state.isBaby ? CUSTOM_CAT_OCELOT_BABY_LOCATION : CAT_OCELOT_LOCATION);
        cir.cancel();
    }

    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "Lnet/minecraft/client/model/OcelotModel;", ordinal = 1)
    )
    private static OcelotModel replaceBabyModel(ModelPart modelPart, Operation<OcelotModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabyOcelotModel(context.bakeLayer(ModModelLayers.BABY_OCELOT));
    }


}
