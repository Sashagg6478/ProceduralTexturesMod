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

    @Inject(method = "mainImage", at = @At("HEAD"), cancellable = true)
    private void onGetMainImage(CallbackInfoReturnable<NativeImage> cir) {
        // Генерируем чистый 4x4 шум прямо в память
        NativeImage generated = ProceduralTextureGenerator.generateNoiseTexture(4, 0xFF888888, 0.15f);
        cir.setReturnValue(generated);
    }
}
