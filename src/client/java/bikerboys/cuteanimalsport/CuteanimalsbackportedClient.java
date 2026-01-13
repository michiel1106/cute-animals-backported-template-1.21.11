package bikerboys.cuteanimalsport;

import bikerboys.cuteanimalsport.misc.*;
import bikerboys.cuteanimalsport.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.*;


public class CuteanimalsbackportedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModModelLayers.register();
		Models.register();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.level == null) return;

			for (Entity e : client.level.entitiesForRendering()) {
				if (!(e instanceof Rabbit rabbit)) continue;
				RabbitAnimationAccess access = (RabbitAnimationAccess) rabbit;

				// --- Hop animation ---
				if (rabbit.getJumpCompletion(0.0F) > 0.0F) {
					access.yourmod$getHopAnimationState().startIfStopped(rabbit.tickCount);
					access.yourmod$getIdleHeadTiltAnimationState().stop(); // don't start idle while hopping
				} else {
					access.yourmod$getHopAnimationState().stop();

					// Only count down idle timeout after hop finishes
					int i = ((RabbitAnimationAccess) rabbit).yourmod$getIdleAnimationTimeout();
					i -= 1;

					((RabbitAnimationAccess) rabbit).yourmod$setIdleAnimationTimeout(i);

					// Check if idle animation should play
					if (((RabbitAnimationAccess) rabbit).yourmod$getIdleAnimationTimeout() <= 0 && rabbit.onGround() && rabbit.getLeashHolder() == null && !rabbit.isNoAi()) {
						access.yourmod$getIdleHeadTiltAnimationState().startIfStopped(rabbit.tickCount);
					} else {
						access.yourmod$getIdleHeadTiltAnimationState().stop();
					}
				}

				// Randomize idle timeout like vanilla when starting idle
				access.yourmod$getIdleHeadTiltAnimationState().ifStarted(state -> {
					if (!state.isStarted()) {
						int i = rabbit.getRandom().nextInt(40) + 180;
						((RabbitAnimationAccess) rabbit).yourmod$setIdleAnimationTimeout(i);

					}
				});
			}
		});
	}
}