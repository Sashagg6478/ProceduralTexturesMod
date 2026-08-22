package com.example.mod.mixin;

import com.example.mod.texture.ProceduralTextureGenerator;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(SpriteLoader.class)
public class TextureAtlasMixin {

    // Перехватываем загрузку отдельных спрайтов
    @Inject(method = "loadSprite", at = @At("HEAD"), cancellable = true)
    private static void onLoadSprite(ResourceLocation location, Resource resource, CallbackInfoReturnable<SpriteContents> cir) {
        // Проверяем, относится ли ресурс к блокам/предметам
        if (location.getPath().startsWith("block/") || location.getPath().startsWith("item/")) {
            
            int textureSize = 4; // Разрешение 4x4 для ультра-оптимизации на Mali GPU
            
            // Базовый серый цвет с небольшим шумом для примера
            // (В будущем сделаем маппинг цветов по имени блока)
            int defaultColor = 0xFF808080; // ABGR
            NativeImage generatedImage = ProceduralTextureGenerator.generateNoiseTexture(textureSize, defaultColor, 0.12f);

            // Собираем SpriteContents прямо из памяти без чтения PNG
            SpriteContents customContents = new SpriteContents(
                location,
                new net.minecraft.client.resources.metadata.animation.FrameSize(textureSize, textureSize),
                generatedImage,
                Optional.empty()
            );

            // Отдаём наш процедурный спрайт и отменяем оригинальную загрузку с диска
            cir.setReturnValue(customContents);
        }
    }
}
