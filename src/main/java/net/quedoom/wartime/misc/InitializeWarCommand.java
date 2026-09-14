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
        List<ServerPlayer> players = source.getLevel().players();
        int amountOfPlayers = players.size();
        int playerToGetMace = (int) (Math.random() * (amountOfPlayers + 1));
        int playerToGetMotor = (int) (Math.random() * (amountOfPlayers + 1));
        players.get(playerToGetMace).addItem(ModItems.SUPER_MEGA_RAPER_MACE.toStack());
        players.get(playerToGetMotor).addItem(AllBlocks.CREATIVE_MOTOR.asStack());
        for (ServerPlayer player : players) {
            player.sendSystemMessage(Component.literal(players.get(playerToGetMace).getName().getString() + " har Macen"));
            player.sendSystemMessage(Component.literal(players.get(playerToGetMotor).getName().getString() + " har Motorn"));
        }
        return 1;
    }
}
