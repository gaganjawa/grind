package org.dsa.graph;


import java.util.*;

public class BestBridge {

    public static int bestBridge(List<List<String>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();

        Set<List<Integer>> mainIsland = new HashSet<>();

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid.get(i).get(j) == "L") {
                    Set<List<Integer>> island = bfs(i, j, grid, new HashSet<>());
                    if(island.size() > 0) {
                        mainIsland = island;
                    }
                }
            }
        }

        Set<List<Integer>> visited = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        for(List<Integer> l : mainIsland) {
            q.add(new int[]{l.get(0), l.get(1), 0});
            visited.add(l);
        }

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];
            int dist = cell[2];
            List<Integer> pos = List.of(row, col);

            if(grid.get(row).get(col) == "L" && !mainIsland.contains(pos)) {
                return dist - 1;
            }

            for(int i = 0; i<4; i++) {
                int nrow = row + di[i];
                int ncol = col + dj[i];

                List<Integer> newPos = List.of(nrow, ncol);

                if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= m || visited.contains(newPos)) continue;

                q.add(new int[]{nrow, ncol, dist + 1});
                visited.add(newPos);
            }
        }

        return -1;
    }

    public static Set<List<Integer>> bfs(int row, int col, List<List<String>> grid, Set<List<Integer>> visited) {
        int n = grid.size();
        int m = grid.get(0).size();

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        visited.add(List.of(row, col));

        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        while(!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];

            for(int i=0; i<4; i++) {
                int nr = r + di[i];
                int nc = c + dj[i];

                if(nr >= 0 && nr < n && nc >=0 && nc < m
                        && grid.get(nr).get(nc) == "L" && !visited.contains(List.of(nr, nc))) {
                    q.add(new int[]{nr, nc});
                    visited.add(List.of(nr, nc));
                }
            }
        }
        return visited;
    }
    public static void main(String... args) {
        // this function behaves as `main()` for the 'run' command
        // you may sandbox in this function , but should not remove it

        List<List<String>> grid = List.of(
                List.of("W", "W", "W", "L", "L"),
                List.of("L", "L", "W", "W", "L"),
                List.of("L", "L", "L", "W", "L"),
                List.of("W", "L", "W", "W", "W"),
                List.of("W", "W", "W", "W", "W"),
                List.of("W", "W", "W", "W", "W")
        );
        System.out.println(BestBridge.bestBridge(grid)); // -> 1

    }
}
