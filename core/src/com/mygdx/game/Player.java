package com.mygdx.game;

/**
 * Created by Praveen on 4/14/17.
 */

public abstract class Player {

    private TurnManager turnManager;
    private int team;

    public TurnManager getTurnManager() {
        return turnManager;
    }

    public void setTurnManager(TurnManager turnManager) {
        this.turnManager = turnManager;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public void gridPointClicked(GridPoint point) {}

    public abstract void startMove();
}
