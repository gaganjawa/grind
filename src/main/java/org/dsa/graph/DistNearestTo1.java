package org.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DistNearestTo1 {

    public static void main(String[] args) {
//        3 4
//        0 0 0 1
//        0 0 1 1
//        0 1 1 0
//

    }
    public static ArrayList<ArrayList<Integer>> nearest(ArrayList<ArrayList<Integer>> mat, int n, int m) {

        int[][] vis = new int[n][m];
        ArrayList<ArrayList<Integer>> dist = new ArrayList<>();;

        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(mat.get(i).get(j) == 1) {
                    q.add(new int[]{i, j, 0});
                }
            }
        }

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        while(!q.isEmpty()) {
            int[] data = q.poll();
            int row = data[0];
            int col = data[1];
            int steps = data[2];
            dist.get(row).add(col, steps);

            for(int i=0; i<4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                if(nrow >=0 && ncol >= 0 && nrow <n && ncol < m && vis[nrow][ncol] == 0 && mat.get(nrow).get(ncol) != 1) {
                    vis[nrow][ncol] = 1;
                    q.add(new int[]{nrow, ncol, steps+1});
                }
            }
        }

        return dist;
    }
}
