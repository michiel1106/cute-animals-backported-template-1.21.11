package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.sheep.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.model.animal.sheep.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(SheepRenderer.class)
public class SheepRendererMixin {
    @Unique
    private static final Identifier SHEEP_BABY_LOCATION = Constants.of("textures/entity/sheep/sheep_baby.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/SheepRenderState;)Lnet/minecraft/resources/Identifier;", at = @At("HEAD"), cancellable = true)
    private void changeSheepTexture(SheepRenderState sheepRenderState, CallbackInfoReturnable<Identifier> cir) {
        if (sheepRenderState.isBaby) {
            cir.setReturnValue(SHEEP_BABY_LOCATION);
        }
    }


    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "Lnet/minecraft/client/model/animal/sheep/SheepModel;", ordinal = 1)
    )
    private static SheepModel replaceBabyModel(ModelPart modelPart, Operation<SheepModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabySheepModel(context.bakeLayer(ModModelLayers.BABY_SHEEP));
    }

}

