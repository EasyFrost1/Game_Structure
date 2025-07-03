package fr.easyfrost.minigame.data;

import fr.easyfrost.minigame.Main;
import fr.easyfrost.minigame.arena.Arena;
import fr.easyfrost.minigame.arena.ArenaManager;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

public class ArenaJson {

    public static void writeAllArenaJson() {

        try {
            File configDir = new File(Main.path);
            if (!configDir.exists()) {
                if (!configDir.mkdirs()) return;
            }

            JSONObject arenaJson = new JSONObject();
            for (Arena arena : ArenaManager.getArenas()) arenaJson.put("" + arena.getName(), arena.getJson());

            File arenaFile = new File(configDir, "arena.json");
            try (FileWriter file = new FileWriter(arenaFile)) {
                file.write(arenaJson.toString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read arena.json and create all arena
     */
    public static void readAllArenaJson() {
        try {
            File configDir = new File(Main.path + "arena.json");
            if (!configDir.exists()) return;

            JSONTokener jsonTokener = new JSONTokener(new FileReader(configDir));
            if (!jsonTokener.more()) return;

            JSONObject arenasJson = new JSONObject(jsonTokener);

            Iterator<String> it = arenasJson.keys();
            String idArena;
            while (it.hasNext()) {
                idArena = it.next();
                if (!ArenaManager.arenaExist(idArena)) {
                    // TODO Read Arena
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
