package net.quedoom.wartime;

public class SavedData {

    private static boolean isWar;

    public static <T> void save(String type, T value) {
        switch (type) {
            case "isWar" -> isWar = (boolean) value;
            default -> {}
        }
    }

}
