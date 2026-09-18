package net.quedoom.wartime.misc;

import com.google.common.collect.BoundType;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.infrastructure.data.CreateMountedItemStorageTypeTagsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import net.quedoom.wartime.SavedData;
import net.quedoom.wartime.Wartime;
import net.quedoom.wartime.init.ModItems;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

public class InitializeWarCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("initwar")
                        .executes(context1 -> giveItem(context1.getSource()))
        );
    }

    private static int giveItem(CommandSourceStack source) {
        boolean isWar = source.getLevel().getGameRules().getRule(SavedData.RULES_ISWAR).get();
        if (isWar) {
            source.getPlayer().sendSystemMessage(Component.literal("War started already!"));
            return 67;
        };
        source.getLevel().getGameRules().getRule(SavedData.RULES_ISWAR).set(true, source.getServer());
        source.getPlayer().addItem(ModItems.SUPER_MEGA_RAPER_MACE.toStack());
        source.getPlayer().addItem(AllBlocks.CREATIVE_FLUID_TANK.asStack());
        return 1;
    }
}
