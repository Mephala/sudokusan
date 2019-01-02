package com.gokhanozg.sudokusan;

import java.util.List;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class PossibleLSudokuSolutions {

    private List<LSudoku> possibleSolutions;
    private int solutionSize;

    public PossibleLSudokuSolutions(List<LSudoku> possibleSolutions, int solutionSize) {
        this.possibleSolutions = possibleSolutions;
        this.solutionSize = solutionSize;
    }

    public List<LSudoku> getPossibleSolutions() {
        return possibleSolutions;
    }

    public void setPossibleSolutions(List<LSudoku> possibleSolutions) {
        this.possibleSolutions = possibleSolutions;
    }

    public int getSolutionSize() {
        return solutionSize;
    }

    public void setSolutionSize(int solutionSize) {
        this.solutionSize = solutionSize;
    }

    @Override
    public String toString() {
        return "PossibleLSudokuSolutions{" +
                "solutionSize=" + solutionSize +
                '}';
    }
}
