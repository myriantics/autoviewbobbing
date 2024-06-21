package net.myriantics.autoviewbobbing.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class AutoPerspectiveMixin {
    @Shadow @Final public GameOptions options;

    @Inject(at = @At("HEAD"), method = "handleInputEvents")
    private void handleInputEvents(CallbackInfo info) {
        if(!this.options.getPerspective().isFirstPerson() && !this.options.getBobView().getValue()) {
            this.options.getBobView().setValue(true);
        } else if (this.options.getPerspective().isFirstPerson() && this.options.getBobView().getValue()){
            this.options.getBobView().setValue(false);
        }
    }
}
