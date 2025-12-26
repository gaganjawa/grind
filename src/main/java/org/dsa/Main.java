package org.dsa;

import java.math.BigInteger;
import java.util.*;

public class Main {
    public static <T> void main(String[] args) {

        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));

        Map<Character, Integer> filteredS = new HashMap<>();

        List<Character> filteredSlist = new ArrayList<>(filteredS.keySet());

//        Double.NEGATIVE_INFINITY;
        LinkedList<Integer> ll = new LinkedList<>();
        int[] arr = new int[2];
        List<Integer> ar = new ArrayList<>();
//        ar.removeLast();
    }

    class Pair
    {
        int weight;
        int value;
        Pair(int weight, int value)
        {
            this.weight = weight;
            this.value = value;
        }

    }

    static class Item {
        int id;
        int time;

        public Item(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    public int solution(int[] T) {
        // Implement your solution here
        int n = T.length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.add(new int[]{i, T[i]});
        }

        int currentTime = 0;
        int[] waitingTime = new int[n];

        while (!q.isEmpty()) {
            int[] currItem = q.poll();
            int idx = currItem[0];
            int timeRemaining = currItem[1];

            currentTime++;

            if (timeRemaining == 1) {
                waitingTime[idx] = currentTime;
            } else {
                q.add(new int[] {idx, timeRemaining - 1});
            }
        }

        int totalWaitTime = 0;
        for (int time : waitingTime) {
            totalWaitTime += time;
        }
        return totalWaitTime;
    }
}