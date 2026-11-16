package com.yourname.fakefps.mixin;

import com.yourname.fakefps.FakeFpsMod;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {
    private static final Random random = new Random();

    @Inject(method = "getCurrentFps", at = @At("RETURN"), cancellable = true)
    private void onGetFps(CallbackInfoReturnable<Integer> cir) {
        if (FakeFpsMod.baseFps > 0) {
            int offset = random.nextInt(31) - 15; // -15 to +15
            int fake = FakeFpsMod.baseFps + offset;
            if (fake < 1) fake = 1;
            cir.setReturnValue(fake);
        }
    }
}
