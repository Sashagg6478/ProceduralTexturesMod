package com.example.mod.mixin;

import com.example.mod.texture.ProceduralTextureGenerator;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpriteContents.class)
public class TextureAtlasMixin {

    @Inject(method = "originalImage", at = @At("HEAD"), cancellable = true, remap = true)
    private void onGetOriginalImage(CallbackInfoReturnable<NativeImage> cir) {
        // Подменяем текстуру прямо при запросе оригинала
        NativeImage generatedImage = ProceduralTextureGenerator.generateNoiseTexture(4, 0xFF888888, 0.15f);
        cir.setReturnValue(generatedImage);
    }
}
