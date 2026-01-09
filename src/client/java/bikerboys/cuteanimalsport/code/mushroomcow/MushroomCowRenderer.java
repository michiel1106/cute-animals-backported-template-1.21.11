package bikerboys.cuteanimalsport.code.mushroomcow;


import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.model.animal.cow.BabyCowModel;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.MushroomCowMushroomLayer;
import net.minecraft.client.renderer.entity.state.MushroomCowRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.cow.MushroomCow.Variant;

public class MushroomCowRenderer extends AgeableMobRenderer<MushroomCow, MushroomCowRenderState, CowModel> {
    private static final Map<Variant, MushroomCowRenderer.MushroomCowTexture> TEXTURES = Util.make(
            Maps.newHashMap(),
            map -> {
                map.put(
                        Variant.BROWN,
                        new MushroomCowTexture(
                                Identifier.withDefaultNamespace("textures/entity/cow/mooshroom_brown.png"),
                                Identifier.withDefaultNamespace("textures/entity/cow/mooshroom_brown_baby.png")
                        )
                );
                map.put(
                        Variant.RED,
                        new MushroomCowTexture(
                                Identifier.withDefaultNamespace("textures/entity/cow/mooshroom_red.png"), Identifier.withDefaultNamespace("textures/entity/cow/mooshroom_red_baby.png")
                        )
                );
            }
    );

    public MushroomCowRenderer(final EntityRendererProvider.Context context) {
        super(context, new CowModel(context.bakeLayer(ModelLayers.MOOSHROOM)), new BabyCowModel(context.bakeLayer(ModelLayers.MOOSHROOM_BABY)), 0.7F);
        this.addLayer(new MushroomCowMushroomLayer(this, context.getBlockRenderDispatcher()));
    }

    public Identifier getTextureLocation(final MushroomCowRenderState state) {
        return state.isBaby
                ? ((MushroomCowRenderer.MushroomCowTexture)TEXTURES.get(state.variant)).baby
                : ((MushroomCowRenderer.MushroomCowTexture)TEXTURES.get(state.variant)).adult;
    }

    public MushroomCowRenderState createRenderState() {
        return new MushroomCowRenderState();
    }

    public void extractRenderState(final MushroomCow entity, final MushroomCowRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.variant = entity.getVariant();
    }

    private record MushroomCowTexture(Identifier adult, Identifier baby) {
    }
}
