package com.example.mod.texture;

import com.mojang.blaze3d.platform.NativeImage;
import java.util.Random;

public class ProceduralTextureGenerator {

    /**
     * Генерирует процедурную текстуру с шумом в памяти
     * @param size Размер стороны (4 для 4x4, 8 для 8x8)
     * @param baseColor Основной цвет в формате ABGR (0xFF_BB_GG_RR)
     * @param noiseFactor Разброс шума (например, 0.15f)
     */
    public static NativeImage generateNoiseTexture(int size, int baseColor, float noiseFactor) {
        NativeImage image = new NativeImage(NativeImage.Format.RGBA, size, size, false);
        Random random = new Random();

        // Извлекаем каналы из ABGR
        int alpha = (baseColor >> 24) & 0xFF;
        int blue  = (baseColor >> 16) & 0xFF;
        int green = (baseColor >> 8)  & 0xFF;
        int red   = baseColor & 0xFF;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // Генерируем небольшое отклонение яркости для каждого пикселя
                float factor = 1.0f + (random.nextFloat() * 2.0f - 1.0f) * noiseFactor;

                int r = Math.min(255, Math.max(0, (int)(red * factor)));
                int g = Math.min(255, Math.max(0, (int)(green * factor)));
                int b = Math.min(255, Math.max(0, (int)(blue * factor)));

                // Формируем ABGR обратно
                int pixelColor = (alpha << 24) | (b << 16) | (g << 8) | r;
                image.setPixelRGBA(x, y, pixelColor);
            }
        }

        return image;
    }
}
