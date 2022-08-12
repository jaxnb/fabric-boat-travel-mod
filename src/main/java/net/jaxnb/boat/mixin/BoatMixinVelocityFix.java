package net.jaxnb.boat.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatEntity.class)
abstract class BoatMixinVelocityFix {

	@Redirect(method = "updatePaddles", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
	private void redirectedSetVelocity(BoatEntity entity, Vec3d velocity) {
		BoatMixinAccessors boat = (BoatMixinAccessors) entity;
		if(!boat.getPressingLeft() && !boat.getPressingRight() && boat.getYawVelocity() < 2) {
			if (entity.getYaw() > 0 - 3 && entity.getYaw() < 0 + 3) {
				entity.setVelocity(0, velocity.getY(), velocity.getZ());
				entity.setYaw(0);
				boat.setYawVelocity(0);
				return;
			} else if (entity.getYaw() > 90 - 3 && entity.getYaw() < 90 + 3) {
				entity.setVelocity(velocity.getX(), velocity.getY(), 0);
				entity.setYaw(90);
				boat.setYawVelocity(0);
				return;
			} else if (entity.getYaw() > 180 - 3 || entity.getYaw() < -180 + 3) {
				entity.setVelocity(0, velocity.getY(), velocity.getZ());
				entity.setYaw(180);
				boat.setYawVelocity(0);
				return;
			} else if (entity.getYaw() > -90 - 3 && entity.getYaw() < -90 + 3) {
				entity.setVelocity(velocity.getX(), velocity.getY(), 0);
				entity.setYaw(-90);
				boat.setYawVelocity(0);
				return;
			}
		}
		entity.setVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
	}
}
