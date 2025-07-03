package fr.easyfrost.minigame.cmd;

import fr.easyfrost.minigame.arena.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (strings.length == 1) return new ArrayList<>(Arrays.asList("create", "remove", "start", "stop"));
        if(strings.length == 2) {
            if(strings[1].equalsIgnoreCase("remove") || strings[1].equalsIgnoreCase("start")) return ArenaManager.getArenaName();
        }
        return List.of();
    }
}
