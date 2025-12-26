package org.dsa;

import java.util.*;

public class CourseScheduleIV {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] edge : prerequisites) {
            int a = edge[0];
            int b = edge[1];
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }

        List<Boolean> ans = new ArrayList<>();

        for(int[] query : queries) {
            ans.add(isReachable(graph, query[0], query[1]));
        }
        return ans;
    }

    public boolean isReachable(Map<Integer, List<Integer>> graph, int u, int v) {
        if(u == v) {
            return true;
        }

        if(!graph.containsKey(u)) {
            return false;
        }

        Queue<List<Integer>> q = new LinkedList<>();
        q.add(graph.get(u));

        while(!q.isEmpty()) {
            int a = q.peek().get(0);
            int b = q.poll().get(1);
            if (v == a || v == b) {
                return true;
            }
            if(graph.containsKey(b)) {
                q.add(graph.get(b));
            }
        }

        return false;
    }

}
