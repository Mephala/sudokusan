package com.gokhanozg.sudokusan;

import com.google.common.collect.Collections2;

import java.util.*;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class LSudoku {

    private Integer[][] vals;

    public LSudoku(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Integer i7, Integer i8, Integer i9) {
        vals = new Integer[3][3];
        vals[0][0] = i1;
        vals[0][1] = i2;
        vals[0][2] = i3;
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

    public LSudoku(LSudoku lSudoku) {
        vals = new Integer[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                vals[i][j] = lSudoku.get(i, j);
            }
        }
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(vals[i][j] == null ? "-" : vals[i][j]);
                if (j != 2) {
                    sb.append(" ");
                } else {
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }


    public List<LSudoku> generatePossibleSolutions() {
        Set<Integer> missingNumbers = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            missingNumbers.add(i);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Integer v = vals[i][j];
                if (v != null) {
                    missingNumbers.remove(v);
                }
            }
        }
        List<LSudoku> retval = new ArrayList<>();
        Collection<List<Integer>> possiblePermutations = Collections2.permutations(missingNumbers);
        for (List<Integer> possiblePermutation : possiblePermutations) {
            LSudoku lSudoku = new LSudoku(this);
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Integer v = vals[i][j];
                    if (v == null) {
                        lSudoku.set(possiblePermutation.get(index), i, j);
                        index++;
                    }
                }
            }
            retval.add(lSudoku);
        }
        return retval;
    }
}
