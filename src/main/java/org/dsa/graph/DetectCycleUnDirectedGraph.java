package org.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleUnDirectedGraph {

    public static void main(String[] args) {

//        3
//        3 2
//        1 2
//        2 3
//        4 0
//        4 3
//        1 4
//        4 3
//        3 1




    }

    public static String cycleDetection(int[][] edges, int n, int m) {

        boolean[] vis = new boolean[2*m];

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                if (detectCycle(i, edges, vis)) {
                    return "Yes";
                }
            }
        }

        return "No";

    }

    public static boolean detectCycle(int src, int[][] edges, boolean[] vis) {

        vis[src] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{src, -1});

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int node = pair[0];
            int parent = pair[1];

            for (int adjNode : edges[node]) {
                if (!vis[adjNode]) {
                    vis[adjNode] = true;
                    q.add(new int[]{adjNode, node});
                }

                if (adjNode != parent) {
                    return true;
                }
            }
        }

        return false;
    }
}