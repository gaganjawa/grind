package org.dsa;

import java.util.*;

public class FourSum {

    public static void main(String[] args) {

        FourSum fourSum = new FourSum();

        int [] nums = new int[]{1,0,-1,0,-2,2};
        int target = 0;

        System.out.println(fourSum.fourSum(nums, target));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0; i<nums.length; i++) {
            if(i != 0 && nums[i-1] != nums[i]) {
                ans = kSum(nums, target, 0, 4);
            }
        }

        return ans;
    }

    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {

        List<List<Integer>> ans = new ArrayList<>();

        if(start == nums.length) {
            return ans;
        }

        int avg = target/k;

        if(nums[start] > avg || avg > nums[nums.length - 1]) {
            return ans;
        }

        if(k == 2) {
            return twoSum(nums, target);
        }

        for(int i = start; i<nums.length; i++) {
            if(i == start || nums[i] != nums[i-1]) {
                for(List<Integer> subsets : kSum(nums, target - nums[i], i+1, k - 1)) {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i])));
                    ans.get(ans.size() - 1).addAll(subsets);
                }
            }
        }

        return ans;
    }

    public List<List<Integer>> twoSum(int[] nums, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> pairs = new ArrayList<>();
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            int sum = nums[left] + nums[right];

            if(sum == target) {
                pairs.add(nums[left]);
                pairs.add(nums[right]);
                ans.add(pairs);
                left++;
                right--;
            } else if(sum < target) {
                left++;
                while(left < right && nums[left] == nums[left+1]) left++;
            } else {
                right--;
                while(left < right && nums[right] == nums[right-1]) right--;
            }
        }


        ans.add(pairs);
        return ans;
    }
}
