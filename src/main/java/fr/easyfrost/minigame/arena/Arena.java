package fr.easyfrost.minigame.arena;

import fr.easyfrost.minigame.arena.edit.EditingMod;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private boolean isEditable;

    private final String name;
    private final List<UUID> players;
    private final Option option;
    private Location waitingLobby;

    public Arena(String nameArena) {
        this.name = nameArena;
        players = new LinkedList<>();
        option = new Option();
    }

    /* ************************************************** */
    /* ***               CLASS METHODS                *** */
    /*                                                    */
    /* ************************************************** */

    public void joinArena(UUID uuid) {
        players.add(uuid);
    }

    public void leaveArena(UUID uuid) {
        players.remove(uuid);
    }

    public boolean startGame() {
        if (!option.isEnabled()) return false;

        return true;
    }

    /* **************************************** */
    /* ***    Visibility for editing mod    *** */
    /*                                          */
    /* **************************************** */

    public void enableVisibility(Player player) {
        List<Location> locationList = new LinkedList<>();

        if (this.getWaitingLobby() != null) {
            player.sendBlockChange(this.getWaitingLobby(), Material.GREEN_GLAZED_TERRACOTTA.createBlockData());
            locationList.add(this.getWaitingLobby());
        }

        // TODO For each spawn point of player mod or others send a fakeblocks to player so he can see it

        EditingMod.getFakeBlocksPerPlayer().put(player.getName(), locationList);
    }

    /* ************************************************** */
    /* ***             GETTERS & SETTERS              *** */
    /*                                                    */
    /* ************************************************** */

    public String getName() {
        return name;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public Option getOption() {
        return option;
    }

    public Location getWaitingLobby() {
        return waitingLobby;
    }

    public void setWaitingLobby(Location waitingLobby) {
        this.waitingLobby = waitingLobby;
    }

}
