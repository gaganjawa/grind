package org.dsa.arrays;

import java.util.ArrayList;
import java.util.List;

public class LengthOfSubArrayWithSum {

    public static void main(String[] args) {

//        5 15
//        6 8 9 5 3
        int n = 5, x = 15;
        List<Integer> nums = List.of(6, 8, 9, 5, 3);
        System.out.println(minSubarray(nums, n, x));
    }

    public static List<Integer> minSubarray(List<Integer> nums, int n, int x)
    {
        int left = 0, right = 0;
        int sum = nums.get(0);
        int minLen = Integer.MAX_VALUE;
        int[] range = new int[2];
        range[0] = -1;
        range[1] = -1;

        while(right < n) {
            while(left <= right && sum > x) {
                if(minLen > (right - left + 1)) {
                    minLen = right - left + 1;
                    range[0] = left;
                    range[1] = right;
                }
                sum -= nums.get(left++);
            }

            right++;
            if(right < n) {
                sum += nums.get(right);
            }
        }

        List<Integer> ans = new ArrayList<>();
        if(range[0] != -1 && range[1] != -1) {
            for(int i=range[0]; i<=range[1]; i++) {
                ans.add(nums.get(i));
            }
        }

        return ans;
    }
}
