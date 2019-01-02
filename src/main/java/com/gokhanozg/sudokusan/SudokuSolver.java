package com.gokhanozg.sudokusan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        LSudoku lSudoku1 = new LSudoku(6, 5, 3, 8, null, 7, null, null, null);
        LSudoku lSudoku2 = new LSudoku(4, null, null, 2, null, null, null, null, 5);
        LSudoku lSudoku3 = new LSudoku(7, null, null, null, null, null, 4, null, null);
        LSudoku lSudoku4 = new LSudoku(1, null, 4, 7, null, null, null, null, null);
        LSudoku lSudoku5 = new LSudoku(null, null, 6, null, 2, null, 3, null, null);
        LSudoku lSudoku6 = new LSudoku(null, null, null, null, null, 5, 1, null, 9);
        LSudoku lSudoku7 = new LSudoku(null, null, 2, null, null, null, null, null, 1);
        LSudoku lSudoku8 = new LSudoku(6, null, null, null, null, 1, null, null, null);
        LSudoku lSudoku9 = new LSudoku(null, null, null, 3, null, 6, 9, 8, 4);
        GSudoku gSudoku = new GSudoku(lSudoku1, lSudoku2, lSudoku3, lSudoku4, lSudoku5, lSudoku6, lSudoku7, lSudoku8, lSudoku9);

        Map<Integer, PossibleLSudokuSolutions> positionalSudokuSolutions = new HashMap<>();
        List<LSudoku> lSudokuList = new ArrayList<>();
        lSudokuList.add(lSudoku1);
        lSudokuList.add(lSudoku2);
        lSudokuList.add(lSudoku3);
        lSudokuList.add(lSudoku4);
        lSudokuList.add(lSudoku5);
        lSudokuList.add(lSudoku6);
        lSudokuList.add(lSudoku7);
        lSudokuList.add(lSudoku8);
        lSudokuList.add(lSudoku9);


        List<PossibleLSudokuSolutions> possibleLSudokuSolutions = new ArrayList<>();


        List<LSudoku> possibleSolutions = lSudoku1.generatePossibleSolutions();
        possibleSolutions.removeIf(next -> !gSudoku.isValid(0, next));
        PossibleLSudokuSolutions possibleLSudokuSolution1 = new PossibleLSudokuSolutions(possibleSolutions, possibleLSudokuSolutions.size());
        possibleLSudokuSolutions.add(possibleLSudokuSolution1);


        for (LSudoku possibleSolution : possibleSolutions) {
            possibleSolution.print();
        }
    }
}
