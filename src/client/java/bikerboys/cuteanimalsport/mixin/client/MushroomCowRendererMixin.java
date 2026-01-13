package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import bikerboys.cuteanimalsport.models.cow.*;
import bikerboys.cuteanimalsport.registry.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.llamalad7.mixinextras.sugar.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(MushroomCowRenderer.class)
public class MushroomCowRendererMixin {


    @Unique
    private static final ResourceLocation BROWN_COW_BABY = Constants.of("textures/entity/cow/mooshroom_brown_baby.png");
    @Unique
    private static final ResourceLocation RED_COW_BABY = Constants.of("textures/entity/cow/mooshroom_red_baby.png");


    @WrapOperation(
            method = "<init>",
            at = @At(value = "NEW", target = "Lnet/minecraft/client/model/CowModel;", ordinal = 1)
    )
    private static CowModel replaceBabyModel(ModelPart modelPart, Operation<CowModel> original, @Local(argsOnly = true) EntityRendererProvider.Context context) {
        return new BabyCowModel(context.bakeLayer(ModModelLayers.BABY_MOOSHROOM));
    }


    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/MushroomCowRenderState;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void getCustomTexture(MushroomCowRenderState state, CallbackInfoReturnable<ResourceLocation> cir) {
        if (state.isBaby) {

            if (state.variant == MushroomCow.Variant.BROWN) {
                cir.setReturnValue(BROWN_COW_BABY);
            } else if (state.variant == MushroomCow.Variant.RED) {
                cir.setReturnValue(RED_COW_BABY);
            }

        }
    }



}
