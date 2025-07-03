package fr.easyfrost.minigame.arena;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private final String name;
    private final List<UUID> players;
    private final Option option;


    public Arena(String nameArena) {
        this.name = nameArena;
        players = new LinkedList<>();
        option = new Option();
    }

    /* ************************************************** */
    /* ***               CLASS METHODS                *** */
    /*                                                    */
    /* ************************************************** */

    public void joinArena(UUID uuid){
        players.add(uuid);
    }

    public void leaveArena(UUID uuid){
        players.remove(uuid);
    }

    public boolean startGame() {
        if(!option.isEnabled()) return false;

        return true;
    }

    public void stopGame() {

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


}
