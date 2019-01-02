package com.gokhanozg.sudokusan;

import java.util.*;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class SudokuSolver {

    private static final int BRUTE_FORCE_LIMIT = 100000;

    public static void main(String[] args) {

        try {
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

            computePossibleSolutions(gSudoku, positionalSudokuSolutions, lSudokuList);
            solveWithInitialPossibilities(positionalSudokuSolutions, lSudokuList);
        } catch (Throwable t) {
            t.printStackTrace();
        }
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

    private static void solveWithInitialPossibilities(Map<Integer, PossibleLSudokuSolutions> positionalSudokuSolutions, List<LSudoku> baseSudokuList) throws InterruptedException {
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
        finalSolution.print();
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
