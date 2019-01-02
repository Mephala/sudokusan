package com.gokhanozg.sudokusan;

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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int li = i / 3;
                int lj = j / 3;
                LSudoku ls = lSudokus[li][lj];

            }
        }
    }
}
