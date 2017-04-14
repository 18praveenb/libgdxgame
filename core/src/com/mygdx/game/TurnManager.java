package com.mygdx.game;

/**
 * Created by Praveen on 4/14/17.
 */

public class TurnManager {
    private Level level;
    private int turn = -1;

    public TurnManager(Level level) {
        this.level = level;
        for (Player p : level.getPlayers()) {
            System.out.println(p);
            p.setTurnManager(this);
        }
    }

    public Level getLevel() {
        return level;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isValid(Move move) {
        Unit unit = move.getFrom().valueIn(level.getUnits());
        int movesRequired = move.getFrom().nonDiagonalDistance(move.getTo());
        return movesRequired < unit.getMoves();
    }

    public void nextTurn() {
        turn = (turn + 1) % level.getPlayers().length;
        level.getPlayers()[turn].startMove();
    }

    public void gridPointClicked(GridPoint point) {
        level.getPlayers()[turn].gridPointClicked(point);
    }

}
