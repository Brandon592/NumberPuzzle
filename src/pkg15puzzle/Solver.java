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
public class Solver {

    public Solver(Board b) {
        puzzle = b;
        goal = new Board(puzzle.getRows(), puzzle.getColumns());
    }

    public void depthFirst() {
        open = new ArrayList();
        closed = new ArrayList();
        solution = new ArrayList();
        closed.add(puzzle);
        solved = false;
        this.depthFirstRecursive(puzzle);
        if (solution.size() > 0) {
            while (solution.get(0).parent != null) {
                solution.add(0, solution.get(0).parent);
            }
        }
    }

    private void depthFirstRecursive(Board current) {
        if (current.equals(goal)) {
            solution.add(current);
            solved = true;
        } else {
            for (Board.Move move : Board.Move.values()) {
                if (!solved && current.canMove(move) && current.getPathLength() < 25) {
                    open.add(0, new Board(current));
                    open.get(0).move(move);
                    if (closed.contains(open.get(0))) {
                        open.remove(0);
                    } else {
                        closed.add(open.remove(0));
                        depthFirstRecursive(closed.get(closed.size() - 1));
                    }
                }
            }
        }
    }

    public void breadthFirst() {
        open = new ArrayList();
        closed = new ArrayList();
        solution = new ArrayList();
        open.add(puzzle);
        while (!open.get(0).equals(goal)) {
            closed.add(open.remove(0));
            for (Board.Move move : Board.Move.values()) {
                if (closed.get(closed.size() - 1).canMove(move)) {
                    open.add(new Board(closed.get(closed.size() - 1)));
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

    private boolean alreadyVisitedState(Board current, Board visited) {
        while (visited != null) {
            if (current.equals(visited)) {
                return true;
            }
            visited = visited.parent;
        }
        return false;
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

    private ArrayList<Board> closed = new ArrayList();
    private ArrayList<Board> open = new ArrayList();
    private ArrayList<Board> solution = new ArrayList();
    private boolean solved;
    private final Board puzzle;
    private final Board goal;
}
