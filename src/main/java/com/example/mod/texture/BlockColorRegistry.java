package com.example.mod.texture;

import java.util.HashMap;
import java.util.Map;

public class BlockColorRegistry {

    private static final Map<String, Integer> COLOR_MAP = new HashMap<>();
    
    // Цвет по умолчанию (если блока нет в реестре)
    private static final int DEFAULT_COLOR = 0xFF808080; // Серый (ABGR)

    static {
        // Формат цвета: 0xFF_BB_GG_RR (ABGR)
        COLOR_MAP.put("block/grass_block_top", 0xFF388B5A);  // Зеленый
        COLOR_MAP.put("block/dirt", 0xFF2D4C8B);             // Коричневый
        COLOR_MAP.put("block/stone", 0xFF808080);            // Серый
        COLOR_MAP.put("block/oak_planks", 0xFF3A659C);       // Дерево
        COLOR_MAP.put("block/cobblestone", 0xFF666666);      // Булыжник
        COLOR_MAP.put("block/sand", 0xFF66C2D6);             // Песок
        COLOR_MAP.put("block/water_still", 0xFFE66633);      // Вода
    }

    public static int getColorForPath(String path) {
        return COLOR_MAP.getOrDefault(path, DEFAULT_COLOR);
    }
}
