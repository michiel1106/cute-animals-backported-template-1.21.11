package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.sheep.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.sheep.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(SheepRenderer.class)
public abstract class SheepRendererMixin extends AgeableMobRenderer<Sheep, SheepRenderState, SheepModel>{
    @Unique
    private static final ResourceLocation SHEEP_BABY_LOCATION = Constants.of("textures/entity/sheep/sheep_baby.png");

    public SheepRendererMixin(EntityRendererProvider.Context context, SheepModel entityModel, SheepModel entityModel2, float f) {
        super(context, entityModel, entityModel2, f);
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/SheepRenderState;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void changeSheepTexture(SheepRenderState sheepRenderState, CallbackInfoReturnable<ResourceLocation> cir) {
        if (sheepRenderState.isBaby) {
            cir.setReturnValue(SHEEP_BABY_LOCATION);
        }
    }

    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "Lnet/minecraft/client/model/SheepModel;", ordinal = 1)
    )
    private static SheepModel replaceBabyModel(ModelPart modelPart, Operation<SheepModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabySheepModel(context.bakeLayer(ModModelLayers.BABY_SHEEP));
    }

}

