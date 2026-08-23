package com.example.mod.texture;

import net.minecraft.client.renderer.texture.NativeImage;

public class ProceduralTextureGenerator {

    public static NativeImage generateNoiseTexture(int size, int baseColor, float noiseFactor) {
        NativeImage image = new NativeImage(size, size, false);
        
        // Извлекаем каналы
        int alpha = (baseColor >> 24) & 0xFF;
        int red   = (baseColor >> 16) & 0xFF;
        int green = (baseColor >> 8)  & 0xFF;
        int blue  = baseColor & 0xFF;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                float factor = 1.0f + (float) ((Math.random() * 2 - 1) * noiseFactor);
                
                int r = Math.min(255, Math.max(0, (int) (red * factor)));
                int g = Math.min(255, Math.max(0, (int) (green * factor)));
                int b = Math.min(255, Math.max(0, (int) (blue * factor)));

                // ABGR формат для NativeImage
                int pixelColor = (alpha << 24) | (b << 16) | (g << 8) | r;
                image.setPixelRGBA(x, y, pixelColor);
            }
        }
        return image;
    }
}
