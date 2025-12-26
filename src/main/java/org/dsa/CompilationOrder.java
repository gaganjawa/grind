package org.dsa;

import java.util.*;

public class CompilationOrder {

    public static void main(String[] args) {

//        [["B","A"],["C","A"],["D","C"],["E","D"],["E","B"]]
        List<List<Character>> dep = new ArrayList<>();
        dep.add(Arrays.asList('B', 'A'));
        dep.add(Arrays.asList('C', 'A'));
        dep.add(Arrays.asList('D', 'C'));
        dep.add(Arrays.asList('E', 'D'));
        dep.add(Arrays.asList('E', 'B'));
        System.out.println(findCompilationOrder(dep));
    }

    public static List<Character> findCompilationOrder(List<List<Character>> dependencies) {

        boolean[] vis = new boolean[676];
        Set<Character> result = new HashSet<>();
        for (int i = 0; i < dependencies.size(); i++) {
            Character curr = dependencies.get(i).get(0);
            topSort(dependencies, curr, vis, result);
        }
        return new ArrayList<>(result);
    }

    static void topSort(List<List<Character>> dependencies, Character curr, boolean[] vis, Set<Character> stack) {
        vis[curr] = true;
        for(List<Character> list : dependencies) {
            Character c = list.get(1);
            if (!vis[c]) {
                topSort(dependencies, c, vis, stack);
            }
        }
        stack.add(curr);
    }
}
