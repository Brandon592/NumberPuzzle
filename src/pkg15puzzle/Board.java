/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Brandon
 */
public class Board {

    public enum Move {LEFT, UP, RIGHT, DOWN}
    
    public Board() {
        this(4,4);
    }

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns =  columns;
        tiles = new int[rows][columns];
        for (int i = 0; i<rows; i++){
            for (int j =  0; j<columns; j++){
                tiles[i][j] = ((columns*i) + j + 1)%(rows*columns);
            }
        }
        currentx = rows - 1;
        currenty = columns - 1;
        parent = null;
        pathLength = 0;
    }

    public Board(int rows, int columns, int... elements) {
        this.rows = rows;
        this.columns =  columns;
        tiles = new int[rows][columns];
        for (int i = 0; i<rows; i++){
            for (int j =  0; j<columns; j++){
                tiles[i][j] = elements[((columns*i) + j)];
            }
        }
        parent = null;
        pathLength = 0;
    }
    
    public Board(Board b){
        this.rows = b.rows;
        this.columns = b.columns;
        tiles = new int[rows][columns];
        parent = b;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                tiles[i][j] = b.tiles[i][j];
                if (tiles[i][j] == 0){currentx = i; currenty = j;}
            }  
        }
        pathLength = b.pathLength + 1;
    }
    
    public boolean canMove(Move m){
        switch (m){
            case LEFT:
                if (currenty == 0)
                    return false;
                return true;
            case UP:
                if (currentx == 0)
                    return false;
                return true;
            case RIGHT:
                if (currenty == rows-1)
                    return false;
                return true;
            case DOWN:
                if (currentx == columns-1)
                    return false;
                return true;
        }
        return false;
    }
    
    public void move(Move m){
        int temp;
        switch (m){
            case LEFT:
                temp = tiles[currentx][currenty-1];
                tiles[currentx][currenty-1] = 0;
                tiles[currentx][currenty] = temp;
                currenty--;
                break;
            case UP:
                temp = tiles[currentx-1][currenty];
                tiles[currentx-1][currenty] = 0;
                tiles[currentx][currenty] = temp;
                currentx--;
                break;
            case RIGHT:
                temp = tiles[currentx][currenty+1];
                tiles[currentx][currenty+1] = 0;
                tiles[currentx][currenty] = temp;
                currenty++;
                break;
            case DOWN:
                temp = tiles[currentx+1][currenty];
                tiles[currentx+1][currenty] = 0;
                tiles[currentx][currenty] = temp;
                currentx++;
        }
    }
    
    @Override
    public boolean equals(Object b){
        if (b == null){return false;}
        if (getClass() != b.getClass()){return false;}
        final Board other = (Board) b;
        return (Arrays.deepEquals(other.tiles, tiles));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Arrays.deepHashCode(this.tiles);
        return hash;
    }
    
    @Override
    public String toString(){
        String ret = "";
        for (int i = 0; i<rows;i++){
            for (int j = 0; j<columns; j++){
                ret = ret.concat(String.format("%d ", tiles[i][j]));
            }
            ret = ret.concat("\n");
        }
        return ret;
    }
    
    public void randomize(){
        Random r = new Random();
        for (int i = 0; i<20; i++){
            int d = r.nextInt(4);
            switch (d){
                case 0:
                    if (canMove(Move.LEFT))
                        move(Move.LEFT);
                    break;
                case 1:
                    if (canMove(Move.UP))
                        move(Move.UP);
                    break;
                case 2:
                    if (canMove(Move.RIGHT))
                        move(Move.RIGHT);
                    break;
                case 3:
                    if (canMove(Move.DOWN))
                        move(Move.DOWN);
                    break;
            }
        }
    }

    public int getPathLength() {
        return pathLength;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
    
    
 
    private int rows;
    private int columns;
    private int currentx;
    private int currenty;
    private int[][] tiles;
    public Board parent;
    private int pathLength;
}
