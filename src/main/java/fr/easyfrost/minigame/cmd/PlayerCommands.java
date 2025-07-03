package fr.easyfrost.minigame.cmd;

import fr.easyfrost.minigame.arena.ArenaManager;
import fr.easyfrost.minigame.inv.PlayerMenuInv;
import fr.easyfrost.minigame.utils.TypeMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) return false;

        if (strings.length == 0) {
            // TODO Menu d'informations
            return true;
        }

        switch (strings[0]) {

            case "list" -> {
                player.openInventory(PlayerMenuInv.getArenaInventory());
                return true;
            }

            case "join" -> {
                if(strings.length == 2) {
                    if(ArenaManager.joinArena(strings[1], player.getUniqueId())) {
                        TypeMessage.feedback(player, "You joined the arena " + strings[1]);
                        return true;
                    }
                }
            }

            case "leave" -> {
                if(strings.length == 1) {
                    if(ArenaManager.leaveArena(player.getUniqueId())) {
                        TypeMessage.feedback(player, "You left the arena");
                        return true;
                    }
                }
            }
        }

        TypeMessage.error(player, TypeMessage.commandError);
        return false;
    }
}
