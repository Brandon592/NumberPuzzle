/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

/**
 *
 * @author Brandon
 */
public class TilesInPlace implements Heuristic {

    @Override
    public int eval(Board b) {
        int val = 0;
        Board goal = new Board(b.getRows(), b.getColumns());
        for (int i =  0; i < b.getRows(); i++){
            for (int j = 0; j < b.getColumns(); j++){
                if (goal.getTile(i, j) == b.getTile(i, j)){
                    val -= 1;
                }
            }
        }
        return val;
    }

    @Override
    public int compare(Board o1, Board o2) {
        return (o1.getPathLength()+eval(o1)-(o2.getPathLength()+eval(o2)));
    }
    
}
