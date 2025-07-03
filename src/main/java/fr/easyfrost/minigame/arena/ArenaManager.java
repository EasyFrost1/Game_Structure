package fr.easyfrost.minigame.arena;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ArenaManager {

    private static final List<Arena> arenas = new ArrayList<>();

    /* ************************************************** */
    /* ***               MANAGE ARENA                 *** */
    /*                                                    */
    /* ************************************************** */
    /**
     * Create an arena if name not already used
     *
     * @param nameArena name of the arena to create
     * @return true if created, false else
     */
    public static boolean createArena(String nameArena) {

        if (arenaExist(nameArena)) return false;
        Arena newArena = new Arena(nameArena);
        arenas.add(newArena);

        return true;
    }

    /**
     * Delete an arena if name exists
     *
     * @param nameArena name of arena to delete
     * @return true if deleted, false else
     */
    public static boolean deleteArena(String nameArena) {
        if (!arenaExist(nameArena)) return false;

        arenas.remove(getArena(nameArena));
        return true;
    }


    /**
     * Get the arena correspond to nameArena
     *
     * @param nameArena name of arena to get
     * @return Arena if exists, null else
     */
    public static Arena getArena(String nameArena) {
        for (Arena arena : arenas) if (arena.getName().equalsIgnoreCase(nameArena)) return arena;

        return null;
    }

    /**
     * Get the arena where player is
     * @param uuid  uuid of player to find game
     * @return
     */
    public static Arena getArena(UUID uuid) {
        for (Arena arena : arenas) if (arena.getPlayers().contains(uuid)) return arena;

        return null;
    }

    /**
     * Check if arena already exist
     *
     * @param nameArena name of arena to find
     * @return true if exists, false else
     */
    public static boolean arenaExist(String nameArena) {
        return getArena(nameArena) != null;
    }


    /**
     * If arena exist, start the arena
     * @param nameArena name of arena to start
     * @return true if started, false else
     */
    public static boolean startArena(String nameArena) {
        Arena arena = getArena(nameArena);
        if(arena == null)  return false;

        return arena.startGame();
    }

    /**
     * Stop the game
     * @param nameArena name of arena to stop
     * @return true if stopped, false else
     */
    public static boolean stopArena(String nameArena) {
        Arena arena = getArena(nameArena);
        if(arena == null)  return false;

        arena.stopGame();
        return true;
    }

    /**
     * Enable the arena if condition is respected
     * @param nameArena name of arena to enable
     * @return true if arena is enabled, false else
     */
    public static boolean enableArena(String nameArena) {
        Arena arena = getArena(nameArena);
        if(arena == null)  return false;

        // TODO Do verification
        arena.getOption().setEnabled(true);
        return true;
    }

    /**
     * Disable the arena
     * @param nameArena name of arena to dsable
     * @return true if disabled, false else
     */
    public static boolean disableArena(String nameArena) {
        Arena arena = getArena(nameArena);
        if(arena == null)  return false;

        arena.getOption().setEnabled(false);
        return true;
    }
    /* ************************************************** */
    /* ***        PLAYER ARENA MANAGEMENT             *** */
    /*                                                    */
    /* ************************************************** */

    /**
     * If arena exist add player to playerList
     *
     * @param nameArena name of arena to join
     * @param uuid  uuid of player to add
     * @return true if added, false else
     */
    public static boolean joinArena(String nameArena, UUID uuid) {
        Arena arena = getArena(nameArena);
        if (arena == null) return false;
        arena.joinArena(uuid);

        return true;
    }

    /**
     * If arena exist remove player to list
     * @param uuid  uuid of player to remove
     * @return true if removed, false else
     */
    public static boolean leaveArena(UUID uuid) {
        Arena arena = getArena(uuid);
        if (arena == null) return false;
        arena.leaveArena(uuid);

        return true;
    }

    /* STRING METHODS */

    /**
     * Get all the active arena name on list
     *
     * @return an array with arena name
     */
    public static List<String> getActiveArenaName() {
        List<String> list = new LinkedList<>();

        for(Arena arena : arenas) {
            if(arena.getOption().isEnabled()) list.add(arena.getName());
        }

        return list;
    }

    /**
     * Get all the arena name on list
     * @return an array with arena name
     */
    public static List<String> getArenaName() {
        List<String> list = new LinkedList<>();

        for(Arena arena : arenas) {
            list.add(arena.getName());
        }

        return list;
    }

    /* ************************************************** */
    /* ***             GETTERS & SETTERS              *** */
    /*                                                    */
    /* ************************************************** */

    public static List<Arena> getArenas() {
        return arenas;
    }


}
