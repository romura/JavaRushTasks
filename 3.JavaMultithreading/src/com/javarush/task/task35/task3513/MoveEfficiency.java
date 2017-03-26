package com.javarush.task.task35.task3513;

/**
 * Created by GETMAN on 23.03.2017.
 */
public class MoveEfficiency implements Comparable<MoveEfficiency>{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        if (o == null) return -1;
        MoveEfficiency e = (MoveEfficiency) o;
        if (numberOfEmptyTiles == e.numberOfEmptyTiles)
            return Integer.compare(score, e.score);
        return Integer.compare(numberOfEmptyTiles, e.numberOfEmptyTiles);
    }
}
