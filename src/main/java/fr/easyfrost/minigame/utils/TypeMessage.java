package fr.easyfrost.minigame.utils;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public class TypeMessage {

    private static final String decoration = Color.PURPLE + "--------------------------\n";

    public static final String commandError = "Incorrect commands";

    /**
     * Send at player the error message with generic format
     *
     * @param player  player to send
     * @param message message to send
     */
    public static void error(Player player, String message) {
        player.sendMessage(Color.RED + "ERROR" + ": " + Color.AQUA + message);
    }

    /**
     * Send at player info message with generic format
     *
     * @param player
     * @param message
     */
    public static void feedback(Player player, String message) {
        player.sendMessage(Color.ORANGE + "INFO"+ ": " + Color.AQUA + message);
    }

    public static void gameMessage(Player player, String message) {
        player.sendMessage(Color.OLIVE + "GAME" + ": " + Color.AQUA + message);
    }

    /**
     * Print all type of list with generic format
     *
     * @param player
     * @param listObj   list to print
     * @param nameList  name of thing printed
     * @param formatter use to explicit how to make object of list to string
     */
    public static <T> void printList(Player player, List<T> listObj, String nameList, Function<T, String> formatter) {
        StringBuilder listString = new StringBuilder();
        for (T element : listObj) {
            listString
                    .append(formatter.apply(element))
                    .append("\n");
        }
        player.sendMessage( "List " + nameList + ":");
        player.sendMessage(listString.toString());
    }
}
