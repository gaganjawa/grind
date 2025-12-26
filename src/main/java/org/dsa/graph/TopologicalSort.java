package org.dsa.graph;

import java.util.*;


/**
 * Printing topological sort using DFS
 */
public class TopologicalSort {

    public static void main(String[] args) {
        TopologicalSort topologicalSort = new TopologicalSort();
        String[] words = new String[] {"wrt","wrf","er","ett","rftt"};
        System.out.println(topologicalSort.topSortOrder(words));
        words = new String[] {"z", "x"};
        System.out.println(topologicalSort.topSortOrder(words));
        words = new String[] {"zxy", "xwv", "wvuts", "utsrqp", "srqponm", "ponmlkji", "nmlkjihgf", "jihgfedcba"};
        System.out.println(topologicalSort.topSortOrder(words));
        words = new String[] {
                "abc", "bcd", "cde", "def", "efg", "fgh", "ghi", "hij"
        };
        System.out.println(topologicalSort.topSortOrder(words));
    }

    public String topSortOrder(String[] words) {

        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.computeIfAbsent(c, k -> new HashSet<>());
            }
        }

        for(int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    graph.get(w1.charAt(j)).add(w2.charAt(j));
                }
            }
        }

        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for(char curr : graph.keySet()) {
            if(!visited[curr - 'a']) {
                topSortUtil(graph, visited, curr, stack);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();

    }

    public void topSortUtil(Map<Character, Set<Character>> graph, boolean[] visited, char curr, Stack<Character> stack) {

        visited[curr - 'a'] = true;

        for (Character c : graph.get(curr)) {
            if (!visited[c - 'a']) {
                topSortUtil(graph, visited, c, stack);
            }
        }
        stack.push(curr);
    }
}
