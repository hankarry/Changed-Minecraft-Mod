package net.ltxprogrammer.changed.client.renderer.animate.camera;

import net.ltxprogrammer.changed.client.CameraExtender;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import static net.ltxprogrammer.changed.client.renderer.animate.wing.DragonWingCreativeFlyAnimator.BODY_FLY_SCALE;
import static net.ltxprogrammer.changed.client.renderer.animate.wing.DragonWingCreativeFlyAnimator.WING_FLAP_RATE;

public class DragonCameraCreativeFlyAnimator<T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> extends HumanoidAnimator.CameraAnimator<T, M> {
    @Override
    public HumanoidAnimator.AnimateStage preferredStage() {
        return HumanoidAnimator.AnimateStage.CREATIVE_FLY;
    }

    @Override
    public boolean requiresViewBob() {
        return true;
    }

    @Override
    public void setupAnim(@NotNull CameraExtender camera, @NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float flapAmount = Mth.cos(ageInTicks * WING_FLAP_RATE);
        flapAmount = Mth.map(flapAmount * flapAmount, 0.0f, 1.0f, -BODY_FLY_SCALE, BODY_FLY_SCALE);
        camera.setCameraPosition(
                camera.getCameraPosition().add(0.0f, Mth.lerp(core.flyAmount, 0.0f, -flapAmount) * 0.0625f, 0.0f));
    }
}
