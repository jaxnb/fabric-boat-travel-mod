package net.jaxnb.boat.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BoatEntity.class)
public interface BoatMixinAccessors {
    @Accessor("yawVelocity")
    float getYawVelocity();
    @Accessor("yawVelocity")
    void setYawVelocity(float yawVelocity);
    @Accessor
    boolean getPressingLeft();
    @Accessor
    boolean getPressingRight();
}
