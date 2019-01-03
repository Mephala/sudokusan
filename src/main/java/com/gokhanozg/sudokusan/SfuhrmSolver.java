package com.gokhanozg.sudokusan;

import de.sfuhrm.sudoku.*;

import java.util.List;

/**
 * Created by Gokhan Ozgozen on 03-Jan-19.
 */
public class SfuhrmSolver {

    public static void main(String[] args) {
        Riddle riddle = new GameMatrixFactory().newRiddle();
        riddle.setAll(QuadraticArrays.parse("001800900", "730042005", "000050000", "080010004", "050080070", "400030060", "000020000", "800460053", "007005100"));

        Solver solver = new Solver(riddle);
        List<GameMatrix> solutions = solver.solve();
        System.out.println(solutions);
        for (GameMatrix solution : solutions) {
            byte[][] bytes = solution.getArray();
            int[][] solutionMatrix = SudokuSolver.convertBytes(bytes);
            System.out.println(solutionMatrix != null);
        }
    }


}
