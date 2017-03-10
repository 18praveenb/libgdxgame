package com.mygdx.game;

public class GridItem {
    public boolean isSoldier() {
        return isSoldier;
    }

    public void setSoldier(boolean soldier) {
        isSoldier = soldier;
    }

    private boolean isSoldier;

    public GridItem(boolean isSoldier) {
        this.isSoldier = isSoldier;
    }
}
