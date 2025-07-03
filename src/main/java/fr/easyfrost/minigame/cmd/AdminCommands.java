package fr.easyfrost.minigame.cmd;

import fr.easyfrost.minigame.arena.ArenaManager;
import fr.easyfrost.minigame.utils.TypeMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) return false;

        if (strings.length == 0) {
            // TODO Menu d'informations
            return true;
        }

        String nameArena = strings[1];

        if (strings.length == 2) {

            switch (strings[0]) {

                case "create" -> {
                    if (ArenaManager.createArena(nameArena)) {
                        TypeMessage.feedback(player, "Arena created " + nameArena);
                        return true;
                    }
                }
                case "delete" -> {
                    if (ArenaManager.deleteArena(nameArena)) {
                        TypeMessage.feedback(player, "Arena deleted " + nameArena);
                        return true;
                    }

                }

                case "start" -> {
                    if (ArenaManager.startArena(nameArena)) {
                        TypeMessage.feedback(player, "Arena started " + nameArena);
                        return true;
                    }
                }
                case "stop" -> {
                    if(ArenaManager.stopArena(nameArena)) {
                        TypeMessage.feedback(player, "Arena stopped " + nameArena);
                        return true;
                    }
                }

                case "enable" -> {
                    if(ArenaManager.enableArena(nameArena)) {
                        TypeMessage.feedback(player, "Arena enabled " + nameArena);
                        return true;
                    }
                }
                case "disable" -> {
                    if(ArenaManager.disableArena(nameArena)) {
                        TypeMessage.feedback(player, "Arena disabled " + nameArena);
                        return true;
                    }
                }

                case "edit" -> {

                }
            }
        }

        TypeMessage.error(player, TypeMessage.commandError);
        return false;
    }
}
