package com.gokhanozg.sudokusan;

import java.util.List;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class SolutionStack {

    private PossibleLSudokuSolutions base;
    private int index;
    private List<LSudoku> baseSudokuList;
    private List<LSudoku> dynamicSudokuList;


    public List<LSudoku> getDynamicSudokuList() {
        return dynamicSudokuList;
    }

    public void setDynamicSudokuList(List<LSudoku> dynamicSudokuList) {
        this.dynamicSudokuList = dynamicSudokuList;
    }

    public List<LSudoku> getBaseSudokuList() {
        return baseSudokuList;
    }

    public void setBaseSudokuList(List<LSudoku> baseSudokuList) {
        this.baseSudokuList = baseSudokuList;
    }

    public PossibleLSudokuSolutions getBase() {
        return base;
    }

    public void setBase(PossibleLSudokuSolutions base) {
        this.base = base;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
