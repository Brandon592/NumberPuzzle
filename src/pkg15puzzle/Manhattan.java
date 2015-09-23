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
public class Manhattan implements Heuristic {

    @Override
    public int eval(Board b) {
        int val = 0;
        Board goal = new Board(b.getRows(), b.getColumns());
        for (int i =  0; i < b.getRows(); i++){
            for (int j = 0; j < b.getColumns(); j++){
                Position g = goal.positionOf(b.getTile(i, j));
                val += (Math.abs(i-g.x)+ Math.abs(j-g.y));
            }
        }
        return val;
    }

    @Override
    public int compare(Board b1, Board b2) {
        return (b1.getPathLength()+eval(b1)-(b2.getPathLength()+eval(b2)));
    }
    
}
