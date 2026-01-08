package bikerboys.cuteanimalsport.registry;

import bikerboys.cuteanimalsport.models.base.*;
import bikerboys.cuteanimalsport.models.ocelot.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;

public class Models {

    public static void register() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BABY_OCELOT, BabyOcelotModel::createBabyLayer);

    }

}
