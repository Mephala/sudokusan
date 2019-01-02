package com.gokhanozg.sudokusan;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class SudokuSolver {

    private static final int BRUTE_FORCE_LIMIT = 100000;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        try {
            long start = System.nanoTime();
            String sudokuString = "-,-,-,-,-,-,-,-,1,-,-,-,-,-,3,-,2,-,-,-,-,-,8,5,-,-,-,-,-,-,-,-,4,-,9,-,5,-,7,-,-,-,-,-,-,-,-,-,1,-,-,-,-,-,5,-,-,-,-,2,-,-,-,-,-,-,-,1,-,-,4,-,-,7,3,-,-,-,-,-,9";
//            String antiBruteForceString = "-,-,-,-,-,-,-,-,1,-,-,-,-,-,3,-,2,-,-,-,-,-,8,5,-,-,-,-,-,-,-,-,4,-,9,-,5,-,7,-,-,-,-,-,-,-,-,-,1,-,-,-,-,-,5,-,-,-,-,2,-,-,-,-,-,-,-,1,-,-,4,-,-,7,3,-,-,-,-,-,9";
            String[] parse = sudokuString.split(",");
            if (parse.length != 81) {
                throw new IllegalArgumentException("Wrong string, please check (size:" + parse.length + ")");
            }
            int index = 0;
            Integer[] initVals = new Integer[81];
            for (int i = 0; i < 81; i++) {
                String s = parse[i];
                initVals[i] = s.equals("-") ? null : Integer.parseInt(s);
            }
            LSudoku lSudoku1 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku2 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku3 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku4 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku5 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku6 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku7 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku8 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
            LSudoku lSudoku9 = new LSudoku(initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++], initVals[index++]);
//            LSudoku lSudoku1 = new LSudoku(null, null, null, 3, null, null, null, null, 9);
//            LSudoku lSudoku2 = new LSudoku(null, 4, null, null, null, 5, null, null, 1);
//            LSudoku lSudoku3 = new LSudoku(null, null, 5, null, 7, null, 6, 3, null);
//            LSudoku lSudoku4 = new LSudoku(null, null, 2, 4, null, null, null, null, 3);
//            LSudoku lSudoku5 = new LSudoku(null, 5, null, null, 6, null, null, 1, null);
//            LSudoku lSudoku6 = new LSudoku(7, null, null, null, null, 1, 5, null, null);
//            LSudoku lSudoku7 = new LSudoku(null, 6, 4, null, 9, null, 2, null, null);
//            LSudoku lSudoku8 = new LSudoku(5, null, null, 2, null, null, null, 9, null);
//            LSudoku lSudoku9 = new LSudoku(2, null, null, null, null, 4, null, null, null);
//            LSudoku lSudoku1 = new LSudoku(6, 5, 3, 8, null, 7, null, null, null);
//            LSudoku lSudoku2 = new LSudoku(4, null, null, 2, null, null, null, null, 5);
//            LSudoku lSudoku3 = new LSudoku(7, null, null, null, null, null, 4, null, null);
//            LSudoku lSudoku4 = new LSudoku(1, null, 4, 7, null, null, null, null, null);
//            LSudoku lSudoku5 = new LSudoku(null, null, 6, null, 2, null, 3, null, null);
//            LSudoku lSudoku6 = new LSudoku(null, null, null, null, null, 5, 1, null, 9);
//            LSudoku lSudoku7 = new LSudoku(null, null, 2, null, null, null, null, null, 1);
//            LSudoku lSudoku8 = new LSudoku(6, null, null, null, null, 1, null, null, null);
//            LSudoku lSudoku9 = new LSudoku(null, null, null, 3, null, 6, 9, 8, 4);
            solveSudoku(lSudoku1, lSudoku2, lSudoku3, lSudoku4, lSudoku5, lSudoku6, lSudoku7, lSudoku8, lSudoku9);
            long differ = System.nanoTime() - start;
            double seconds = differ / 1000000000d;
            System.out.println("Solved puzzle in " + BigDecimal.valueOf(seconds).setScale(3, BigDecimal.ROUND_HALF_UP).toPlainString() + " seconds.");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void solveSudoku(LSudoku lSudoku1, LSudoku lSudoku2, LSudoku lSudoku3, LSudoku lSudoku4, LSudoku lSudoku5, LSudoku lSudoku6, LSudoku lSudoku7, LSudoku lSudoku8, LSudoku lSudoku9) throws InterruptedException {
        GSudoku gSudoku = new GSudoku(lSudoku1, lSudoku2, lSudoku3, lSudoku4, lSudoku5, lSudoku6, lSudoku7, lSudoku8, lSudoku9);
        System.out.println("***** SOLVING THIS SUDOKU *****");
        gSudoku.print();

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

        computePossibleSolutions(gSudoku, positionalSudokuSolutions, lSudokuList);
        solveWithInitialPossibilities(positionalSudokuSolutions, lSudokuList, gSudoku);
    }

    private static void computePossibleSolutions(GSudoku gSudoku, Map<Integer, PossibleLSudokuSolutions> positionalSudokuSolutions, List<LSudoku> lSudokuList) throws InterruptedException {
        List<Thread> sudokuThreads = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                LSudoku lSudoku = lSudokuList.get(finalI);
                if (!lSudoku.isSolved()) {
                    List<LSudoku> possibleSolutions = lSudoku.generatePossibleSolutions();
                    possibleSolutions.removeIf(next -> !gSudoku.isValid(finalI, next));
                    PossibleLSudokuSolutions possibleLSudokuSolutions = new PossibleLSudokuSolutions(possibleSolutions, possibleSolutions.size());
                    positionalSudokuSolutions.put(finalI, possibleLSudokuSolutions);
                }
            });
            sudokuThreads.add(t);
            t.start();
        }

        for (Thread sudokuThread : sudokuThreads) {
            sudokuThread.join();
        }
    }

    private static void solveWithInitialPossibilities(Map<Integer, PossibleLSudokuSolutions> positionalSudokuSolutions, List<LSudoku> baseSudokuList, GSudoku gSudoku) throws InterruptedException {
        Map<Integer, LSudoku> compatibleSolutions = new HashMap<>();
        Stack<LSudoku> lSudokuStack = new Stack<>();
        Map<Integer, Integer> solutionIndex = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            solutionIndex.put(i, 0);
        }
        while (lSudokuStack.size() != 9) {
            int currentSize = lSudokuStack.size();
            List<LSudoku> currentPossibles = positionalSudokuSolutions.get(currentSize).getPossibleSolutions();
            if (solutionIndex.get(currentSize) == currentPossibles.size()) {
                lSudokuStack.pop();
                continue;
            }
            LSudoku trial = currentPossibles.get(solutionIndex.get(currentSize));
            lSudokuStack.push(trial);
            compatibleSolutions.put(currentSize, trial);
            solutionIndex.put(currentSize, solutionIndex.get(currentSize) + 1); // incrementing
            List<LSudoku> tmpLsudoku = new ArrayList<>(baseSudokuList);
            for (int i = 0; i < currentSize; i++) {
                tmpLsudoku.set(i, compatibleSolutions.get(i));
            }
            tmpLsudoku.set(currentSize, trial);
            Map<Integer, PossibleLSudokuSolutions> tmpPotentialSolutions = new HashMap<>();
            GSudoku tmpGsudoku = new GSudoku(tmpLsudoku.get(0), tmpLsudoku.get(1), tmpLsudoku.get(2), tmpLsudoku.get(3), tmpLsudoku.get(4), tmpLsudoku.get(5), tmpLsudoku.get(6), tmpLsudoku.get(7), tmpLsudoku.get(8));
            computePossibleSolutions(tmpGsudoku, tmpPotentialSolutions, tmpLsudoku);
            if (!isPotentialSolutionsValid(tmpPotentialSolutions)) {
                lSudokuStack.pop();
            } else {
                for (int i = currentSize + 1; i < 9; i++) {
                    positionalSudokuSolutions.put(i, tmpPotentialSolutions.get(i));
                    solutionIndex.put(i, 0);
                }
            }
        }
        List<LSudoku> solutionLSudokus = new ArrayList<>();
        while (!lSudokuStack.empty()) {
            solutionLSudokus.add(lSudokuStack.pop());
        }
        GSudoku finalSolution = new GSudoku(solutionLSudokus.get(8), solutionLSudokus.get(7), solutionLSudokus.get(6), solutionLSudokus.get(5), solutionLSudokus.get(4), solutionLSudokus.get(3), solutionLSudokus.get(2), solutionLSudokus.get(1), solutionLSudokus.get(0));
        System.out.println("****** FINAL SOLUTION ******");
        fancyPrint(finalSolution, gSudoku);
    }

    private static void fancyPrint(GSudoku finalSolution, GSudoku gSudoku) {
        Integer[][] solution = finalSolution.getVals();
        Integer[][] given = gSudoku.getVals();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (given[i][j] != null) {
                    System.out.print(given[i][j]);
                } else {
                    System.out.print(ANSI_RED + solution[i][j] + ANSI_RESET);
                }
                if (j == 8) {
                    System.out.println();
                } else {
                    System.out.print(" ");
                }
            }
        }
    }


    private static boolean isPotentialSolutionsValid(Map<Integer, PossibleLSudokuSolutions> tmpPotentialSolutions) {
        for (Map.Entry<Integer, PossibleLSudokuSolutions> integerPossibleLSudokuSolutionsEntry : tmpPotentialSolutions.entrySet()) {
            if (integerPossibleLSudokuSolutionsEntry.getValue().getSolutionSize() == 0) {
                return false;
            }
        }
        return true;
    }

    private static int findPossibleSolutionKeyWithLowestSize(Map<Integer, PossibleLSudokuSolutions> positionalSudokuSolutions) {
        int lowestKey = -1;
        int lowestSize = Integer.MAX_VALUE;
        for (Map.Entry<Integer, PossibleLSudokuSolutions> integerPossibleLSudokuSolutionsEntry : positionalSudokuSolutions.entrySet()) {
            int size = integerPossibleLSudokuSolutionsEntry.getValue().getSolutionSize();
            int key = integerPossibleLSudokuSolutionsEntry.getKey();
            if (size < lowestSize) {
                lowestKey = integerPossibleLSudokuSolutionsEntry.getKey();
                lowestSize = size;
            }
            System.out.println("Lsudoku:" + key + " has " + size + " possible solutions..");
        }
        System.out.println("**********************");
        return lowestKey;
    }


}
