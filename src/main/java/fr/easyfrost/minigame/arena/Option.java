package fr.easyfrost.minigame.arena;

public class Option {
    private boolean enabled;

    public Option() {
        enabled = false;
    }


    /* ************************************************** */
    /* ***             GETTERS & SETTERS              *** */
    /*                                                    */
    /* ************************************************** */

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
