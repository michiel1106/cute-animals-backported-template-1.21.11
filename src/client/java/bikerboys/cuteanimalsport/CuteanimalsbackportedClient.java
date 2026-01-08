package bikerboys.cuteanimalsport;

import bikerboys.cuteanimalsport.registry.*;
import net.fabricmc.api.ClientModInitializer;

public class CuteanimalsbackportedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModModelLayers.register();
		Models.register();
	}
}