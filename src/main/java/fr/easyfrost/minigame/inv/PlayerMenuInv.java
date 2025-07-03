package fr.easyfrost.minigame.inv;

import fr.easyfrost.minigame.arena.Arena;
import fr.easyfrost.minigame.arena.ArenaManager;
import fr.easyfrost.minigame.utils.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class PlayerMenuInv {

    /**
     * Get an inventory with allactive arenas already created or empty
     * @return inventory with arena list
     */
    public static Inventory getArenaInventory() {
        int sizeInv = ((ArenaManager.getArenas().size() / 9 ) + 1) * 9;

        Inventory inv = Bukkit.createInventory(null, sizeInv, "Arena Inventory");

        for(Arena arena : ArenaManager.getArenas()) {
            if(arena.getOption().isEnabled()) inv.addItem(ItemManager.createGuiItem(Material.WHITE_STAINED_GLASS, 1, arena.getName()));
        }

        if(inv.isEmpty()) inv.addItem(ItemManager.createGuiItem(Material.RED_STAINED_GLASS, 1, "No Arenas"));

        return inv;
    }
}
