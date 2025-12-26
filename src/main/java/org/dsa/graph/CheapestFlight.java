package org.dsa.graph;

import java.util.*;

public class CheapestFlight {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        Map<Integer, List<List<Integer>>> adj = new HashMap<>();
        for(int i=0; i<flights.length; i++) {
            adj.put(i, new ArrayList<>());
        }

        for(int i=0; i<flights[0].length; i++) {
            adj.get(i).add(List.of(flights[i][1], flights[i][2]));
        }


        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        Queue<Tuple> pq = new LinkedList<>();
        pq.add(new Tuple(0, 0, src));

        while(!pq.isEmpty()) {
            Tuple t = pq.poll();
            int stops = t.stops;
            int cost = t.cost;
            int node = t.node;

            if(stops > k) {
                continue;
            }

            if (!adj.containsKey(node)) continue;

            for(List<Integer> pair : adj.get(node)) {
                int adjNode = pair.get(0);
                int adjCost = pair.get(1);

                if(cost + adjCost < prices[adjNode]) {
                    prices[adjNode] = cost + adjCost;
                    pq.add(new Tuple(stops + 1, cost + adjCost, adjNode));
                }
            }
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }

    class Tuple {
        int stops;
        int cost;
        int node;

        public Tuple(int stops, int cost, int node) {
            this.stops = stops;
            this.cost = cost;
            this.node = node;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "stops=" + stops +
                    ", cost=" + cost +
                    ", node=" + node +
                    '}';
        }
    }

}
