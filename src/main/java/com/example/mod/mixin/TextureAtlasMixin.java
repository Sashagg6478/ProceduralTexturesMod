package com.example.mod.mixin;

import com.example.mod.texture.ProceduralTextureGenerator;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpriteLoader.class)
public class TextureAtlasMixin {

    // Добавляем remap = true (или явный метод), чтобы Mixin подтянул нужный метод в runtime
    @Inject(method = "load", at = @At("HEAD"), cancellable = true, remap = true)
    private static void onLoadSprite(ResourceLocation location, Resource resource, CallbackInfoReturnable<NativeImage> cir) {
        // Если путь ведет к текстурам блоков
        if (location.getPath().startsWith("textures/block/")) {
            // Твоя логика подмены на 4x4
            NativeImage generatedImage = ProceduralTextureGenerator.generateNoiseTexture(4, 0xFF888888, 0.15f);
            cir.setReturnValue(generatedImage);
        }
    }
}
