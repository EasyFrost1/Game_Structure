package fr.easyfrost.minigame;

import fr.easyfrost.minigame.cmd.AdminCommands;
import fr.easyfrost.minigame.cmd.PlayerCommands;
import fr.easyfrost.minigame.cmd.PlayerCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

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
