package com.mygdx.game;

/**
 * Created by Praveen on 4/14/17.
 */

public class Move {
    private GridPoint from;
    private GridPoint to;

    public Move(GridPoint from, GridPoint to) {
        this.from = from;
        this.to = to;
    }

    public GridPoint getFrom() {
        return from;
    }

    public GridPoint getTo() {
        return to;
    }
}
