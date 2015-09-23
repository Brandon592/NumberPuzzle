/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brandon
 */
public class Solver {

    public Solver(Board b) {
        puzzle = b;
        goal = new Board(puzzle.getRows(), puzzle.getColumns());
    }

    public void depthFirst() {
        open = new ArrayList();
        closed = new ArrayList();
        solution = new ArrayList();
        states = 0;
        closed.add(puzzle);
        solved = false;
        this.depthFirstRecursive(puzzle, closed);
        if (solution.size() > 0) {
            while (solution.get(0).parent != null) {
                solution.add(0, solution.get(0).parent);
            }
        }
    }

    private void depthFirstRecursive(Board current, ArrayList<Board> closed ) {
        if (current.equals(goal)){
            solution.add(current);
            solved = true;
        }else{
            for (Board.Move move: Board.Move.values()){
                if (!solved && current.getPathLength() < 20){
                    if (current.canMove(move)){
                    Board temp = new Board(current);
                    states++;
                    temp.move(move);
                    if (!closed.contains(temp)){
                        closed.add(temp);
                        this.depthFirstRecursive(temp, closed);
                    }
                    }
                }
            }
        }
    }

    public void breadthFirst() {
        open = new ArrayList();
        closed = new ArrayList();
        solution = new ArrayList();
        states = 0;
        open.add(puzzle);
        while (!open.get(0).equals(goal)) {
            closed.add(open.remove(0));
            for (Board.Move move : Board.Move.values()) {
                if (closed.get(closed.size() - 1).canMove(move)) {
                    open.add(new Board(closed.get(closed.size() - 1)));
                    states++;
                    open.get(open.size() - 1).move(move);
                    if (closed.contains(open.get(open.size() - 1))) {
                        open.remove(open.size() - 1);
                    }
                }
            }
        }
        solution.add(open.remove(0));
        while (solution.get(0).parent != null) {
            solution.add(0, solution.get(0).parent);
        }
    }
    
    public void aStar(Heuristic h){
        open = new ArrayList();
        closed = new ArrayList();
        solution = new ArrayList();
        states = 0;
        open.add(puzzle);
        while (!open.get(0).equals(goal)) {
            closed.add(open.remove(0));
            for (Board.Move move : Board.Move.values()) {
                if (closed.get(closed.size() - 1).canMove(move)) {
                    open.add(new Board(closed.get(closed.size() - 1)));
                    states++;
                    open.get(open.size() - 1).move(move);
                    if (closed.contains(open.get(open.size() - 1))) {
                        if (closed.get(closed.indexOf(open.get(open.size()-1))).getPathLength() >
                                open.get(open.size()-1).getPathLength()){
                            closed.remove(open.get(open.size()-1));
                            closed.add(open.remove(open.size()-1));
                        }else{
                            open.remove(open.size() - 1);
                        }
                    }
                }
            }
            Collections.sort(open, h);
        }
        solution.add(open.remove(0));
        while (solution.get(0).parent != null) {
            solution.add(0, solution.get(0).parent);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        solution.stream().forEach((b) -> {
            sb.append(b);
            sb.append("\n");
        });
        return sb.toString();
    }

    /**
     * Returns a value of -1 if no solution has been recorded
     *
     * @return the number of moves used to reach the solution
     */
    public int getNumMoves() {
        return solution.size() - 1;
    }

    /**
     * @return the number of states visited while searching for a solution
     */
    public int getNumStatesVisited() {
        return closed.size();
    }
    
    public ArrayList<Board> getSolution(){
        return solution;
    }

    public int getStates() {
        return states;
    }

    private ArrayList<Board> closed = new ArrayList();
    private ArrayList<Board> open = new ArrayList();
    private ArrayList<Board> solution = new ArrayList();
    private boolean solved;
    private int states = 0;
    private final Board puzzle;
    private final Board goal;
}