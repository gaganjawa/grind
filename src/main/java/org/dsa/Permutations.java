package org.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        getPerms(nums, 0, ans);
        return ans;
    }

    static void getPerms(int[] nums, int idx, List<List<Integer>> ans) {
        if (idx == nums.length) {
            ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }

        for(int i = idx; i<nums.length; i++) {
            swap(nums, i, idx);
            getPerms(nums, idx+1, ans);
            swap(nums, i, idx);
        }

    }

    static void swap(int[] nums, int i, int idx) {
        int temp = nums[i];
        nums[i] = nums[idx];
        nums[idx] = temp;
    }

}
