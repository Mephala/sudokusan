package com.gokhanozg.sudokusan;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class LSudokuStackObject {
    private LSudoku lSudoku;
    private boolean anchor = false;

    public LSudoku getlSudoku() {
        return lSudoku;
    }

    public void setlSudoku(LSudoku lSudoku) {
        this.lSudoku = lSudoku;
    }

    public boolean isAnchor() {
        return anchor;
    }

    public void setAnchor(boolean anchor) {
        this.anchor = anchor;
    }
}
