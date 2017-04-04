package com.mygdx.game;

/**
 * Created by Praveen on 4/4/17.
 */

public class Player {
    private int team;
    private boolean human;

    public Player(int team, boolean human) {
        this.team = team;
        this.human = human;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
        this.human = human;
    }
}
