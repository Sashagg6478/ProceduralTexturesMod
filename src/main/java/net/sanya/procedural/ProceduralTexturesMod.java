package net.sanya.procedural;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmlmod.FMLJavaModLoadingContext;

@Mod("proceduraltexturesmod")
public class ProceduralTexturesMod {
    public ProceduralTexturesMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Логика инициализации
    }
}
