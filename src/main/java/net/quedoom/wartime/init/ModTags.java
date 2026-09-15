package net.quedoom.wartime.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.quedoom.wartime.Wartime;

public class ModTags {
    public class Blocks {
        public static final TagKey<Block> SUPER_MACE_MINEABLE = create("mineable/super_mace");

        private static TagKey<Block> create(String name) {
            return TagKey.create(
                    // The registry key. The type of the registry must match the generic type of the tag.
                    Registries.BLOCK,
                    // The location of the tag. This example will put our tag at data/examplemod/tags/blocks/example_tag.json.
                    Wartime.id(name)
            );
        }
    }

    public static void register() {
    }
}
