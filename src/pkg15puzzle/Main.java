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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board puzzle =  new Board(3,3);
        puzzle.randomize();
        Solver s = new Solver(puzzle);
        s.depthFirst();
        System.out.println("Depth-First Solution");
        System.out.println(s);
        System.out.printf("It took %d moves\n", s.getNumMoves());
        System.out.printf("%d states were generated\n\n", s.getNumStatesVisited());
        s.breadthFirst();
        System.out.println("Breadth-First Solution");
        System.out.println(s);
        System.out.printf("It took %d moves\n", s.getNumMoves());
        System.out.printf("%d states were generated\n\n", s.getNumStatesVisited());
        /*closed.add(puzzle);
        depthFirst(puzzle);
        while (solution.get(0).parent != null){
            solution.add(0, solution.get(0).parent);
        }
        solution.stream().forEach((b) -> {
            System.out.println(b);
        });
        open = new ArrayList();
        closed = new ArrayList();
        closed.add(puzzle);
        solution = new ArrayList();
        solved = false;
        breadthFirst(puzzle);
        while (solution.get(0).parent != null){
            solution.add(0, solution.get(0).parent);
        }
        System.out.println("Breadth-First Solution");
        solution.stream().forEach((b) -> {
            System.out.println(b);
        });
        System.out.printf("It took %d moves\n", solution.get(solution.size()-1).getPathLength());
        System.out.printf("%d states were generated\n\n", closed.size());
        */
    }
}
