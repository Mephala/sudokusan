package com.gokhanozg.sudokusan;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class GSudoku {
    private LSudoku[][] lSudokus = new LSudoku[3][3];
    private Integer[][] vals = new Integer[9][9];


    public GSudoku(LSudoku l1, LSudoku l2, LSudoku l3, LSudoku l4, LSudoku l5, LSudoku l6, LSudoku l7, LSudoku l8, LSudoku l9) {
        lSudokus[0][0] = l1;
        lSudokus[0][1] = l2;
        lSudokus[0][2] = l3;
        lSudokus[1][0] = l4;
        lSudokus[1][1] = l5;
        lSudokus[1][2] = l6;
        lSudokus[2][0] = l7;
        lSudokus[2][1] = l8;
        lSudokus[2][2] = l9;
        setVals();
    }

    public GSudoku(GSudoku gSudoku) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lSudokus[i][j] = gSudoku.get(i, j);
            }
        }
        setVals();
    }

    public Integer[][] getVals() {
        return vals;
    }

    private void setVals() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int li = i / 3;
                int lj = j / 3;
                LSudoku ls = lSudokus[li][lj];
                int p = i % 3;
                int q = j % 3;
                vals[i][j] = ls.get(p, q);
            }
        }
    }

    public LSudoku get(int i, int j) {
        return lSudokus[i][j];
    }

    public void set(LSudoku lSudoku, int i, int j) {
        lSudokus[i][j] = lSudoku;
        setVals();
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(vals[i][j] == null ? "-" : vals[i][j]);
                if (j != 8) {
                    sb.append(" ");
                } else {
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

    public boolean isValid() {
        for (int i = 0; i < 9; i++) {
            Set<Integer> rowValues = new HashSet<>();
            Set<Integer> columnValues = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                Integer rowVal = vals[i][j];
                Integer colVal = vals[j][i];
                if (rowVal != null && rowValues.contains(rowVal)) {
                    return false;
                } else if (rowVal != null) {
                    rowValues.add(rowVal);
                }
                if (colVal != null && columnValues.contains(colVal)) {
                    return false;
                } else if (colVal != null) {
                    columnValues.add(colVal);
                }
            }
        }
        return true;
    }

    public boolean isValid(int position, LSudoku lSudoku) {
        GSudoku gSudoku = new GSudoku(this);
        int i = position / 3;
        int j = position % 3;
        gSudoku.set(lSudoku, i, j);
        return gSudoku.isValid();
    }
}
