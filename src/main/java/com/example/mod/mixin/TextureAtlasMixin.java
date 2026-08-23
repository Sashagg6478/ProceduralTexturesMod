package com.example.mod.mixin;

import com.example.mod.texture.BlockColorRegistry;
import com.example.mod.texture.ProceduralTextureGenerator;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TextureAtlas.class)
public class TextureAtlasMixin {
    // Скоростной хук без плясок вокруг SpriteLoader
}
