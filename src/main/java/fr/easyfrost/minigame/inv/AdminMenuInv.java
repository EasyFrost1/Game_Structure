package fr.easyfrost.minigame.inv;

import fr.easyfrost.minigame.arena.Arena;
import fr.easyfrost.minigame.arena.ArenaManager;
import fr.easyfrost.minigame.utils.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class AdminMenuInv {

    /**
     * Get an inventory with all arenas already created or empty
     * @return inventory with arena list
     */
    public static Inventory getArenaInventory() {
        int sizeInv = ((ArenaManager.getArenas().size() + 1 / 9 ) + 1) * 9;

        Inventory inv = Bukkit.createInventory(null, sizeInv, "Arena Inventory");

        Material mat = Material.RED_STAINED_GLASS;

        for(Arena arena : ArenaManager.getArenas()) {
            if(arena.getOption().isEnabled()) mat = Material.GREEN_STAINED_GLASS;
            inv.addItem(ItemManager.createGuiItem(mat, 1, arena.getName()));
        }

        inv.setItem(sizeInv -1 ,ItemManager.createGuiItem(Material.BLUE_STAINED_GLASS, 1, Color.BLUE + "Create Arena"));

        return inv;
    }
}
