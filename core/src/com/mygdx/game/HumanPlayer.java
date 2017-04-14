package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Praveen on 4/4/17.
 */

public class HumanPlayer extends Player {
    private boolean active = false;

    public HumanPlayer(int team) {
        setTeam(team);
    }

    public void startMove() {
        this.active = true;
    }

    public void gridPointClicked(GridPoint point) {
        super.gridPointClicked(point);
        if (!active) return;

        point.valueIn(getTurnManager().getLevel().getTiles()).setHighlight(Color.CORAL);
    }
}
