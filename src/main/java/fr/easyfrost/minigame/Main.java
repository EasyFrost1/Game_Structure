package fr.easyfrost.minigame;

import fr.easyfrost.minigame.cmd.AdminCommands;
import fr.easyfrost.minigame.cmd.PlayerCommands;
import fr.easyfrost.minigame.cmd.PlayerCompleter;
import org.bukkit.plugin.java.JavaPlugin;

// Refractor rename "Main" to the type of plugin to develop
public final class Main extends JavaPlugin {

    public final static String path = "plugins/ "+ Main.class.getName() +"/";
    private Main instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("zombie").setExecutor(new PlayerCommands());
        this.getCommand("zombie-admin").setExecutor(new AdminCommands());
        this.getCommand("zombie").setTabCompleter(new PlayerCompleter());
        this.getCommand("zombie-admin").setTabCompleter(new PlayerCompleter());
    }

    @Override
    public void onDisable() {
    }


    public Main getInstance() {
        return instance;
    }
}
