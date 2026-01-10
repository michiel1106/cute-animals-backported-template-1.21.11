package bikerboys.cuteanimalsport.mixin.client.animation;

import bikerboys.cuteanimalsport.misc.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.rabbit.*;
import net.minecraft.world.level.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Rabbit.class)
public abstract class RabbitMixin extends Animal implements RabbitAnimationAccess {

    protected RabbitMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private final AnimationState yourmod$hopAnimationState = new AnimationState();

    @Unique
    private final AnimationState yourmod$idleHeadTiltAnimationState = new AnimationState();

    @Unique
    private int yourmod$idleAnimationTimeout;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void yourmod$init(EntityType<?> type, Level level, CallbackInfo ci) {
        this.yourmod$idleAnimationTimeout = level.random.nextInt(40) + 180;
    }

    @Override
    public AnimationState yourmod$getHopAnimationState() {
        return this.yourmod$hopAnimationState;
    }

    @Override
    public AnimationState yourmod$getIdleHeadTiltAnimationState() {
        return this.yourmod$idleHeadTiltAnimationState;
    }

    @Override
    public int yourmod$getIdleAnimationTimeout() {
        return this.yourmod$idleAnimationTimeout;
    }

    @Override
    public void yourmod$setIdleAnimationTimeout(int value) {
        this.yourmod$idleAnimationTimeout = value;
    }

    @Override
    public void yourmod$decrementIdleAnimationTimeout() {
        this.yourmod$idleAnimationTimeout--;
    }

/*
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void step(CallbackInfo ci) {
        Rabbit rabbit = (Rabbit)(Object)this;

        if (!rabbit.level().isClientSide()) return;

        if (yourmod$shouldPlayIdleAnimation(rabbit)) {
            this.yourmod$idleAnimationTimeout = rabbit.getRandom().nextInt(40) + 180;
            this.yourmod$idleHeadTiltAnimationState.start(rabbit.tickCount);
        } else if (rabbit.getJumpCompletion(0.0F) > 0.0F) {
            this.yourmod$hopAnimationState.startIfStopped(rabbit.tickCount);
            this.yourmod$idleHeadTiltAnimationState.stop();
        } else {
            this.yourmod$idleAnimationTimeout--;
            this.yourmod$hopAnimationState.stop();
        }
    }

 */


    @Unique
    private boolean yourmod$shouldPlayIdleAnimation(Rabbit rabbit) {
        return this.yourmod$idleAnimationTimeout <= 0
                && rabbit.getLeashData() != null
                && rabbit.getLeashData().leashHolder == null
                && !rabbit.isNoAi();
    }
}