package com.gokhanozg.sudokusan.yazarokurwinwin;

import com.gokhanozg.sudokusan.SudokuSolver;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Gokhan Ozgozen on 02-Jan-19.
 */
public class YazarokurAutoSolver {

    private static final Object lock = new Object();
    private static final int SOLVER_THREADS = 26;
    private static final int PUZZLE_ID_LIMIT = 1000000;
    private static final AtomicInteger runningThreads = new AtomicInteger(SOLVER_THREADS);
    private static final AtomicInteger waitingThreads = new AtomicInteger(0);

    public static void main(String[] args) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://uye.yazarokur.com/giris");
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("password", "bbbbbbbbb"));
            params.add(new BasicNameValuePair("email", "mephalay@gmail.com"));
            params.add(new BasicNameValuePair("_d", "signin"));
            post.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(post);
            String responseString = EntityUtils.toString(response.getEntity());
            if (responseString.contains("iripenis")) {
                startSolving(client);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void startSolving(HttpClient client) throws InterruptedException {
        AtomicInteger puzzleId = new AtomicInteger(223456);
        List<Thread> solverThreads = new ArrayList<>();
        for (int i = 0; i < SOLVER_THREADS; i++) {
            Thread t = new Thread(() -> solvePuzzle(client, puzzleId));
            t.start();
            solverThreads.add(t);
        }
        for (Thread solverThread : solverThreads) {
            solverThread.join();
        }
        System.out.println("!!FINITO!!");
    }

    private static void solvePuzzle(HttpClient client, AtomicInteger puzzleId) {
        int puzzleIdInteger = puzzleId.getAndIncrement();
        while (puzzleIdInteger < PUZZLE_ID_LIMIT) {
            try {
                String url = "https://sudoku.yazarokur.com/?sudoku=" + puzzleIdInteger;
                System.out.println("Fetching puzzle from:" + url);
                HttpGet httpGet = new HttpGet(url);
//        HttpGet httpGet = new HttpGet("https://sudoku.yazarokur.com/?sudoku=" + puzzleId.getAndIncrement());
                String puzzleHtml = null;
                HttpResponse httpResponse = null;
                synchronized (lock) {
                    httpResponse = client.execute(httpGet);
                    puzzleHtml = EntityUtils.toString(httpResponse.getEntity());
                    httpResponse.getEntity().consumeContent();
                }
                Integer[] puzzle = parsePuzzle(puzzleHtml);
                int[][] solution = SudokuSolver.solveFrom1DIntegerArray(puzzle);
                int[][][] postSolution = constructPostSolution(solution);
                HttpPost post = new HttpPost("https://sudoku.yazarokur.com/");
                setPostParams(post, puzzleHtml, postSolution);
                runningThreads.decrementAndGet();
                waitingThreads.incrementAndGet();
                System.out.println("Thread waiting for puzzle id:" + puzzleIdInteger + ", runningThreads:" + runningThreads.get() + " , waitingThreads:" + waitingThreads.get());
                Thread.sleep(120000L);
                runningThreads.incrementAndGet();
                waitingThreads.decrementAndGet();
                String postResponse = null;
                synchronized (lock) {
                    httpResponse = client.execute(post);
                    postResponse = EntityUtils.toString(httpResponse.getEntity());
                    httpResponse.getEntity().consumeContent();
                }
                if (postResponse.contains("Tebrik Ederim.") && postResponse.contains("Başarını Paylaş Herkes Duysun")) {
                    System.out.println("Solved:" + puzzleIdInteger);
                } else {
                    System.out.println("Something went wrong with this:" + puzzleIdInteger);
                }
                puzzleIdInteger = puzzleId.getAndIncrement();
            } catch (Throwable t) {
                t.printStackTrace();
            }

        }

    }

    private static void setPostParams(HttpPost post, String puzzleHtml, int[][][] postSolution) throws UnsupportedEncodingException {
        String sudoku = parseValWithName("<input type=hidden name=sudoku value=\"", puzzleHtml);
        String sessionId = parseValWithName("<input type=hidden name=sessionId value=\"", puzzleHtml);
        List<NameValuePair> params = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    String key = "_s[" + i + 1 + "][" + j + 1 + "][" + k + 1 + "]";
                    String value = Integer.valueOf(postSolution[i][j][k]).toString();
                    params.add(new BasicNameValuePair(key, value));
                }
            }
        }
        for (int i = 0; i < 81; i++) {
            params.add(new BasicNameValuePair("n[" + i + "]", null));
        }
        params.add(new BasicNameValuePair("sudoku", sudoku));
        params.add(new BasicNameValuePair("check", "KONTROL ET"));
        params.add(new BasicNameValuePair("sessionId", sessionId));
        post.setEntity(new UrlEncodedFormEntity(params));
    }

    private static String parseValWithName(String s, String puzzleHtml) {
        int index = puzzleHtml.indexOf(s);
        String tag = "\"";
        puzzleHtml = puzzleHtml.substring(index + s.length());
        index = puzzleHtml.indexOf(tag);
        return puzzleHtml.substring(0, index);
    }

    private static int[][][] constructPostSolution(int[][] solution) {
        int[][][] retval = new int[9][3][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int block = ((i / 3) * 3) + j / 3;
                int ri = i % 3;
                int rj = j % 3;
                retval[block][ri][rj] = solution[i][j];
            }
        }
        return retval;
    }

    private static Integer[] parsePuzzle(String puzzleHtml) {
        Integer[] vals = new Integer[81];
        String tag = "n[0]";
        int index = puzzleHtml.indexOf(tag);
        puzzleHtml = puzzleHtml.substring(index + tag.length());
        for (int i = 0; i < 81; i++) {
            tag = "</td>";
            index = puzzleHtml.indexOf(tag);
            String line = puzzleHtml.substring(0, index);
            if (line.contains("value=\"")) {
                tag = "value=\"";
                index = line.indexOf(tag);
                line = line.substring(index + tag.length());
                vals[i] = Integer.parseInt(line.substring(0, 1));
            } else {
                vals[i] = null;
            }
            tag = "<td>";
            index = puzzleHtml.indexOf(tag);
            puzzleHtml = puzzleHtml.substring(index + tag.length());
        }
        return vals;
    }
}
