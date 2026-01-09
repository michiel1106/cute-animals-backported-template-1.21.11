package bikerboys.cuteanimalsport.mixin.client;

import bikerboys.cuteanimalsport.*;
import static bikerboys.cuteanimalsport.mixin.client.PigRendererMixin.*;
import bikerboys.cuteanimalsport.models.cow.*;
import bikerboys.cuteanimalsport.models.pig.*;
import bikerboys.cuteanimalsport.registry.*;
import com.google.common.collect.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.animal.cow.*;
import net.minecraft.client.model.animal.pig.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.animal.cow.*;
import net.minecraft.world.entity.animal.pig.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(CowRenderer.class)
public class CowRendererMixin {


    @Unique
    private static final Identifier BABY_COW_TEMPERATE = Constants.of("textures/entity/cow/cow_temperate_baby.png");
    @Unique
    private static final Identifier BABY_COW_COLD = Constants.of("textures/entity/cow/cow_cold_baby.png");
    @Unique
    private static final Identifier BABY_COW_WARM = Constants.of("textures/entity/cow/cow_warm_baby.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/CowRenderState;)Lnet/minecraft/resources/Identifier;", at = @At("HEAD"), cancellable = true)
    private void getCustomTexture(CowRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (state.isBaby && state.variant != null) {
            ClientAsset.ResourceTexture texture = state.variant.modelAndTexture().asset();

            if (state.variant.modelAndTexture().model() == CowVariant.ModelType.COLD) {
                cir.setReturnValue(BABY_COW_COLD);
            } else if (state.variant.modelAndTexture().model() == CowVariant.ModelType.WARM) {
                cir.setReturnValue(BABY_COW_WARM);
            } else if (state.variant.modelAndTexture().model() == CowVariant.ModelType.NORMAL) {
                cir.setReturnValue(BABY_COW_TEMPERATE);
            }
        }
    }

    @Inject(method = "bakeModels", at = @At("HEAD"), cancellable = true)
    private static void changeModels(EntityRendererProvider.Context context, CallbackInfoReturnable<Map<CowVariant.ModelType, AdultAndBabyModelPair<CowModel>>> cir) {
        EnumMap<CowVariant.ModelType, AdultAndBabyModelPair<CowModel>> var = Maps.newEnumMap(
                Map.of(
                        CowVariant.ModelType.NORMAL,
                        new AdultAndBabyModelPair<>(new CowModel(context.bakeLayer(ModelLayers.COW)), new BabyCowModel(context.bakeLayer(ModModelLayers.BABY_COW))),
                        CowVariant.ModelType.WARM,
                        new AdultAndBabyModelPair<>(new CowModel(context.bakeLayer(ModelLayers.WARM_COW)), new BabyCowModel(context.bakeLayer(ModModelLayers.BABY_COW))),
                        CowVariant.ModelType.COLD,
                        new AdultAndBabyModelPair<>(new CowModel(context.bakeLayer(ModelLayers.COLD_COW)), new BabyCowModel(context.bakeLayer(ModModelLayers.BABY_COW)))
                )
        );
        cir.setReturnValue(var);
    }

}
