package org.dsa.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ShortestPathInUDGraph {

    class Pair{
        int first;
        int second;
        public Pair(int first,int second){
            this.first = first;
            this.second = second;
        }
    }
    public List<Integer> shortestPath(int n, int m, int edges[][]) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0;i<=n;i++) {
            adj.add(new ArrayList<>());
        }
        for(int i = 0;i<m;i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }




        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(Comparator.comparingInt(x -> x.first));

        int[] dist = new int[n+1];
        int[] parent =new int[n+1];
        for(int i = 1;i<=n;i++) {
            dist[i] = (int)(1e9);
            parent[i] = i;
        }

        dist[1] = 0;
        pq.add(new Pair(0, 1));

        while(!pq.isEmpty()) {
            Pair it = pq.poll();
            int node = it.second;
            int dis = it.first;

            for(Pair iter : adj.get(node)) {
                int adjNode = iter.first;
                int edW = iter.second;
                if(dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    pq.add(new Pair(dis + edW, adjNode));
                    parent[adjNode] = node;
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        if(dist[n] == 1e9) {
            path.add(-1);
            return path;
        }

        int node = n;

        while(parent[node] != node) {
            path.add(node);
            node = parent[node];
        }
        path.add(1);

        Collections.reverse(path);
        return path;

    }

}