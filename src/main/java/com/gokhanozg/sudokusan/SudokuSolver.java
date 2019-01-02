package com.gokhanozg.sudokusan;

import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class SudokuSolver {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        Collection<List<Integer>> c = Collections2.permutations(list);
        for (List l : c) {
            System.out.println(l);
        }
    }
}
