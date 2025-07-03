package fr.easyfrost.minigame.arena.edit;

import fr.easyfrost.minigame.arena.Arena;
import fr.easyfrost.minigame.arena.ArenaManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EditingMod {

    // Map with all players on editing mod, and their inventory
    private final static Map<String, ItemStack[]> editingPlayers = new TreeMap<>();

    // Map with all fake blocks send to player, used when player leave editing mod
    private final static Map<String, List<Location>> fakeBlocksPerPlayer = new TreeMap<>();

    /**
     * Return the arena player is editing by checking the first item of their inventory
     *
     * @param player player who's editing
     * @return Arena who player is editing, null if error
     */
    public static Arena getArenaEdited(Player player) {
        String itemName = player.getInventory().getItem(0).getItemMeta().getDisplayName();
        return ArenaManager.getArena(itemName);
    }


    /**
     * Switch the arena players is editing
     *
     * @param player player who's editing
     * @param currentItem item correspond to arena in inventory
     */
    public static void switchArenaEdit(Player player, ItemStack currentItem) {
        Arena arena = getArenaEdited(player);
        if (arena != null) {
            resetVisibility(player);
        }

        EditingMod.addItemEditArena(player);
        player.getInventory().setItem(0, currentItem);
        player.closeInventory();

        Arena newArena = getArenaEdited(player);
        newArena.enableVisibility(player);
    }


    public static void switchMode(Player player) {
        String playerName = player.getName();

        if (editingPlayers.containsKey(playerName)) {
            Arena arena = EditingMod.getArenaEdited(player);
            if (arena != null) {
                resetVisibility(player);
            }

            player.getInventory().clear();

            int i = 0;
            for (ItemStack it : editingPlayers.get(playerName)) {
                if (it != null) player.getInventory().setItem(i, it);
                i++;
            }
            editingPlayers.remove(playerName);

        } else {
            editingPlayers.put(playerName, player.getInventory().getContents());
            addItemEditArena(player);
        }
    }


    /**
     * Reset the fake blocks send to players
     * @param player
     */
    public static void resetVisibility(Player player) {
        List<Location> locList = EditingMod.getFakeBlocksPerPlayer().remove(player.getName());
        if (locList != null) {
            for (Location loc : locList) {
                player.sendBlockChange(loc, loc.getBlock().getBlockData());
            }
        }
    }


    /**
     * Add item for editing the spawn point of arena
     * @param player player who's editing
     */
    public static void addItemEditArena(Player player) {
        Inventory inv = player.getInventory();

        for (int i = 1; i < 9; i++) inv.clear(i);

        // TODO ADD Item for adding spawn point removing it etc
    }

    /* ************************************************** */
    /* ***             GETTERS & SETTERS              *** */
    /*                                                    */
    /* ************************************************** */

    public static Map<String, List<Location>> getFakeBlocksPerPlayer() {
        return fakeBlocksPerPlayer;
    }

    public static Map<String, ItemStack[]> getEditingPlayers() {
        return editingPlayers;
    }
}
