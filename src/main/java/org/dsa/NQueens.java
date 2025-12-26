package org.dsa;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }

        helper(board, n, 0, 0);

        return result;
    }

    void helper(char[][] board, int n, int r, int c) {

        if(r<0 || c<0 || r >=n || c>= n) {
            return;
        }

        if(r == n) {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++) {
                sb.append(board[r][j]);
            }
            result.add(List.of(sb.toString()));
        }

        if(isSafe(board, n, r, c)) {
            board[r][c] = 'Q';
            helper(board, n, r+1, c);
            board[r][c] = '.';
        }

    }

    static boolean isSafe(char[][] grid, int n, int r, int c) {

        for (int i = 0; i < n; i++) {
            if (grid[r][i] == 'Q') {
                return false;
            }
        }

        for (int j = 0; j < n; j++) {
            if (grid[j][c] == 'Q') {
                return false;
            }
        }

        for (int i = r, j = c; i > 0 && j > 0;i--, j--){
            if (grid[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = r, j = c; i > 0 && j < n;i--, j++){
            if (grid[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
