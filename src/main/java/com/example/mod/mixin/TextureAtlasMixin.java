package com.example.mod.mixin;

import com.example.mod.texture.ProceduralTextureGenerator;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.client.resources.metadata.animation.FrameSize;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpriteLoader.class)
public class TextureAtlasMixin {

    @Inject(method = "loadSprite", at = @At("HEAD"), cancellable = true)
    private static void onLoadSprite(ResourceLocation location, Resource resource, CallbackInfoReturnable<SpriteContents> cir) {
        if (location.getPath().startsWith("block/") || location.getPath().startsWith("item/")) {
            
            int textureSize = 4;
            int defaultColor = 0xFF808080; // ABGR
            NativeImage generatedImage = ProceduralTextureGenerator.generateNoiseTexture(textureSize, defaultColor, 0.12f);

            // Использование ResourceMetadata.EMPTY решает проблему совместимости типов Java 21
            SpriteContents customContents = new SpriteContents(
                location,
                new FrameSize(textureSize, textureSize),
                generatedImage,
                ResourceMetadata.EMPTY
            );

            cir.setReturnValue(customContents);
        }
    }
}
