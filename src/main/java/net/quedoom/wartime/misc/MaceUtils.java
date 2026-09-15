package net.quedoom.wartime.misc;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.quedoom.wartime.init.ModTags;

import java.util.List;

public class MaceUtils {

    public static Tool createToolProperties() {
        return new Tool(List.of(
                Tool.Rule.deniesDrops(BlockTags.INCORRECT_FOR_NETHERITE_TOOL),
                Tool.Rule.minesAndDrops(ModTags.Blocks.SUPER_MACE_MINEABLE, 12.5F)),
                5.5F, 1);
    }

    


}
