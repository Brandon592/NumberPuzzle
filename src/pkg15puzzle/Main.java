/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.ArrayList;

/**
 *
 * @author Brandon
 */
public class Main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board puzzle = new Board(boardWidth, boardHeight);
        puzzle.randomize();
        Solver s = new Solver(puzzle);
        s.depthFirst();
        ArrayList<Board> solution = s.getSolution();
        System.out.println("Depth-First Solution");
        System.out.println(s);
        System.out.printf("It took %d moves\n", s.getNumMoves());
        System.out.printf("%d states were generated\n", s.getStates());
        System.out.printf("%d nodes were explored\n\n", s.getNumStatesVisited());
        s.breadthFirst();
        System.out.println("Breadth-First Solution");
        System.out.println(s);
        System.out.printf("It took %d moves\n", s.getNumMoves());
        System.out.printf("%d states were generated\n", s.getStates());
        System.out.printf("%d nodes were explored\n\n", s.getNumStatesVisited());
        s.aStar(new Manhattan());
        System.out.println("Manhattan Distance Solution");
        System.out.println(s);
        System.out.printf("It took %d moves\n", s.getNumMoves());
        System.out.printf("%d states were generated\n", s.getStates());
        System.out.printf("%d nodes were explored\n\n", s.getNumStatesVisited());
        s.aStar(new TilesInPlace());
        System.out.println("Tiles In Place Solution");
        System.out.println(s);
        System.out.printf("It took %d moves\n", s.getNumMoves());
        System.out.printf("%d states were generated\n", s.getStates());
        System.out.printf("%d nodes were explored\n\n", s.getNumStatesVisited());
    }

    private static final int boardWidth = 3;
    private static final int boardHeight = 3;
    private static String[][] labels = new String[boardWidth][boardHeight];
}
