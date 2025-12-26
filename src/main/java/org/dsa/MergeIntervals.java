package org.dsa;

import java.util.LinkedList;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        LinkedList<int[]> result = new LinkedList<>();

        result.add(intervals[0]);

        for(int i=1; i<intervals.length; i++) {
            int[] interval = intervals[i];
            int[] last = result.getLast();

            int currStart = interval[0];
            int currEnd = interval[1];

            int lastEnd = last[1];

            if(currEnd <= lastEnd) {
                last[1] = Math.max(lastEnd, currEnd);
            } else {
                result.add(new int[]{currStart, currEnd});
            }
        }

        return result.toArray(new int[0][]);

    }

}
