package net.quedoom.wartime;

import net.minecraft.world.level.GameRules;

public class SavedData {

    public static final GameRules.Key<GameRules.BooleanValue> RULES_ISWAR = GameRules.register(
            "isWar", GameRules.Category.MISC, GameRules.BooleanValue.create(false)
    );

    public static void register() {

    }

}
