package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * Created by GETMAN on 21.03.2017.
 */
public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
    Stack<Tile[][]> previousStates = new Stack();
    Stack<Integer> previousScores = new Stack();
    boolean isSaveNeeded = true;

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove(){
        if(!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }

        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i-1][j].value) return true;
            }
        }

        return false;
    }

    public Model() {
        resetGameTiles();
    }

    public void resetGameTiles() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        score = 0;
        maxTile = 2;

        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    private void saveState(Tile[][] tiles){
        Tile[][] copyTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                copyTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }

        previousStates.push(copyTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback(){
        if(!previousScores.isEmpty()){
            gameTiles = previousStates.pop();
        }
        if(!previousStates.isEmpty()){
            score = previousScores.pop();
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles.length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            for (int j = 1; j < tiles.length; j++) {
                if (tiles[j - 1].isEmpty() && !tiles[j].isEmpty()) {
                    change = true;
                    tiles[j - 1] = tiles[j];
                    tiles[j] = new Tile();
                }
            }
        }
        return change;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            if ((tiles[i - 1].value == tiles[i].value) && !tiles[i - 1].isEmpty() && !tiles[i].isEmpty()) {
                change = true;
                tiles[i - 1].value *= 2;
                score += tiles[i - 1].value;
                maxTile = maxTile > tiles[i - 1].value ? maxTile : tiles[i - 1].value;
                tiles[i] = new Tile();
                compressTiles(tiles);
            }
        }
        return change;
    }

    private void rotateToRight(Tile[][] tiles){
        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {
                Tile tmp = tiles[i][j];
                tiles[i][j] = tiles[FIELD_WIDTH - 1 - j][i];
                tiles[FIELD_WIDTH - 1 - j][i] = tiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j];
                tiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j] = tiles[j][FIELD_WIDTH - 1 - i];
                tiles[j][FIELD_WIDTH - 1 - i] = tmp;
            }
        }
    }


    void left() {
        if(isSaveNeeded){
            saveState(gameTiles);
        }
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged) addTile();
        isSaveNeeded = true;
    }

    void right(){
        saveState(gameTiles);
        rotateToRight(gameTiles);
        rotateToRight(gameTiles);
        left();
        rotateToRight(gameTiles);
        rotateToRight(gameTiles);
    }

    void up(){
        saveState(gameTiles);
        rotateToRight(gameTiles);
        rotateToRight(gameTiles);
        rotateToRight(gameTiles);
        left();
        rotateToRight(gameTiles);
    }

    void down(){
        saveState(gameTiles);
        rotateToRight(gameTiles);
        left();
        rotateToRight(gameTiles);
        rotateToRight(gameTiles);
        rotateToRight(gameTiles);
    }

    void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;

        switch (n){
            case 0:{
                left();
                break;
            }
            case 1:{
                right();
                break;
            }
            case 2:{
                up();
                break;
            }
            case 3:{
                down();
                break;
            }
        }
    }

    boolean hasBoardChanged(){
        int currentTileWeight = 0;
        int previousTileWeight = 0;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (!gameTiles[i][j].isEmpty()) {
                    currentTileWeight += gameTiles[i][j].value;
                }
            }
        }

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (!previousStates.peek()[i][j].isEmpty()) {
                    previousTileWeight += previousStates.peek()[i][j].value;
                }
            }
        }

        return currentTileWeight != previousTileWeight;
    }

    MoveEfficiency getMoveEfficiency(Move move){
        move.move();
        int numberOfEmptyTiles = getEmptyTiles().size();
        int bufScore = score;
        MoveEfficiency moveEfficiency;

        if (hasBoardChanged()){
            moveEfficiency = new MoveEfficiency(numberOfEmptyTiles, bufScore, move);
        }
        else{
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return moveEfficiency;
    }

    void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::down));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.peek().getMove().move();
    }
}
