/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.Comparator;

/**
 *
 * @author Brandon
 */
public interface Heuristic extends Comparator<Board>{
    public abstract int eval(Board b);
}
