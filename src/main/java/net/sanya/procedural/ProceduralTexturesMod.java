package net.sanya.procedural;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("proceduraltexturesmod")
public class ProceduralTexturesMod {
    public static final String MODID = "proceduraltexturesmod";
    private static final Logger LOGGER = LogManager.getLogger();

    public ProceduralTexturesMod(IEventBus modEventBus) {
        LOGGER.info("Procedural Textures Mod загружен!");
    }
}
