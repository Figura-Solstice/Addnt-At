package dev.solstice.addntAt.mixin;

import org.figuramc.figura.utils.IOUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Mixin(value = org.figuramc.figura.utils.IOUtils.class, remap = false)
public abstract class IOUtilsMixin {
    @Shadow
    public static String getFileNameOrEmpty(Path path) {
        return null;
    }

    @Inject(method = "isHidden", at = @At("HEAD"), cancellable = true)
    private static void isHiddenMixin(Path path, CallbackInfoReturnable<Boolean> cir) {
        if (IOUtils.getFileNameOrEmpty(path).startsWith("@")) {
            cir.cancel();
            cir.setReturnValue(true);
        };
    }
}
