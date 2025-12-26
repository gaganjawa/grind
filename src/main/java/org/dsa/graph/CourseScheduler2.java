package org.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseScheduler2 {
    public static void main(String[] args) {
//        4 4
//        2 1
//        3 1
//        4 2
//        4 3

        List<Integer>[] adj = new ArrayList[6+1];



        ArrayList<Integer> l1 = new ArrayList<>( Arrays.asList(4, 4));
        ArrayList<Integer> l2 = new ArrayList<>( Arrays.asList(2,1));
        ArrayList<Integer> l3 = new ArrayList<>( Arrays.asList(3, 1));
        ArrayList<Integer> l4 = new ArrayList<>( Arrays.asList(4,2));
        ArrayList<Integer> l5 = new ArrayList<>( Arrays.asList(4,3));
        
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(l1);
        edges.add(l2);
        edges.add(l3);
        edges.add(l4);
        edges.add(l5);

        System.out.println(findSchedule(4, edges));

    }

    public static List<Integer> findSchedule(int n, List<List<Integer>> prerequisites) {
        int[] vis = new int[n];
        int[] pathvis = new int[n];

        List<List<Integer>> edges = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            edges.add(new ArrayList<>());
        }

        for(int i=0; i<prerequisites.size(); i++) {
            List<Integer> sub = prerequisites.get(i);
            edges.get(sub.get(0)).add(sub.get(1));
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            if(vis[i] == 0) {
                if(dfs(i, vis, pathvis, edges, list)) {
                    return new ArrayList<>();
                }
            }
        }

        return list;

    }

    public static boolean dfs(int node, int[] vis, int[] pathvis, List<List<Integer>> edges, List<Integer> list) {

        vis[node] = 1;
        pathvis[node] = 1;

        for(int course : edges.get(node)) {
            if(vis[course] != 1 && dfs(course, vis, pathvis, edges, list)) {
                return true;
            } else if(pathvis[course] == 1) {
                return true;
            }
        }
        list.add(node);
        pathvis[node] = 0;

        return false;
    }
}
