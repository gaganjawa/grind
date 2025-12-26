package org.dsa.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;
        int[] parent = new int[n+1];
        int[] rank = new int[n+1];

        for(int i=0; i<=n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            if(!connection(a, b, parent, rank)) {
                return edge;
            }
        }

        return new int[0];
    }

    public boolean connection(int a, int b, int[] parent, int[] rank) {

        int p1 = find(a, parent);
        int p2 = find(b, parent);

        if(p1 == p2) {
            return false;
        }

        if(rank[p1] > rank[p2]) {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        } else {
            parent[p1] = p2;
            rank[p2] += rank[p1];
        }

        return true;
    }

    public int find(int a, int[] parent) {

        while(a != parent[a]) {
            parent[a] = find(parent[a], parent);
        }

        return parent[a];
    }
}
