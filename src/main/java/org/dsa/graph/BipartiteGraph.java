package org.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BipartiteGraph {

    public static void main(String[] args) {

        ArrayList<Integer> l1 = new ArrayList<>( Arrays.asList(0,1,1));
        ArrayList<Integer> l2 = new ArrayList<>( Arrays.asList(0,0,1));
        ArrayList<Integer> l3 = new ArrayList<>( Arrays.asList(0,0,0));

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(l1);
        edges.add(l2);
        edges.add(l3);

        System.out.println(isGraphBirpatite(edges));

    }

    public static boolean isGraphBirpatite(ArrayList<ArrayList<Integer>> edges) {

        int n = edges.size();
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!isBipartite(i, 0, edges, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBipartite(int node, int col, ArrayList<ArrayList<Integer>> graph, int[] color) {

        color[node] = col;

        for (int i = 0; i < graph.get(node).size(); i++) {
            if (graph.get(node).get(i) == 1 && color[i] == -1) {
                if (!isBipartite(i, 1 - col, graph, color)) {
                    return false;
                } else if (color[i] == col) {
                    return false;
                }
            }
        }

        return true;

    }
}