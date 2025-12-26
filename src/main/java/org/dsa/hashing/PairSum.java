package org.dsa.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairSum {

    public static void main(String[] args) {
        System.out.println(PairSum.pairSum(List.of(3, 2, 5, 4, 1), 8));
    }

    public static List<Integer> pairSum(List<Integer> numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < numbers.size(); i++) {
            map.put(numbers.get(i), i);
        }

        List<Integer> ans = new ArrayList<>();

        for(int i=0; i < numbers.size(); i++) {
            int num = numbers.get(i);
            int rem = Math.abs(target - num);
            if(map.containsKey(rem)) {
                ans.add(i, map.get(rem));
                break;
            }
        }
        return ans;
    }
}
