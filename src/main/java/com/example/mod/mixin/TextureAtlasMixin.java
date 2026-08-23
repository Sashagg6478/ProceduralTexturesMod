package com.example.mod.mixin;

import com.example.mod.texture.ProceduralTextureGenerator;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextureAtlasSprite.class)
public class TextureAtlasMixin {

    @Inject(method = "getMainImage", at = @At("HEAD"), cancellable = true, remap = false)
    private void onGetMainImage(CallbackInfoReturnable<NativeImage> cir) {
        NativeImage generated = ProceduralTextureGenerator.generateNoiseTexture(4, 0xFF888888, 0.15f);
        cir.setReturnValue(generated);
    }
}
