package com.gokhanozg.sudokusan;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class LSudoku {
    private Integer[][] vals;

    public LSudoku(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Integer i7, Integer i8, Integer i9) {
        vals = new Integer[3][3];
        vals[0][1] = i1;
        vals[0][2] = i2;
        vals[0][3] = i3;
        vals[1][0] = i4;
        vals[1][1] = i5;
        vals[1][2] = i6;
        vals[2][0] = i7;
        vals[2][1] = i8;
        vals[2][2] = i9;
    }

    public Integer get(int i, int j) {
        return vals[i][j];
    }

    public void set(Integer v, int i, int j) {
        vals[i][j] = v;
    }
}
