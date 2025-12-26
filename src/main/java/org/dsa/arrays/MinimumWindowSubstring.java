package org.dsa.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }
    public static String minWindow(String s, String t) {
        int n = s.length();
        Map<Character, Integer> reqCount = new HashMap<>();
        Map<Character, Integer> filteredS = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for(char c : t.toCharArray()) {
            int count = reqCount.getOrDefault(c, 0);
            reqCount.put(c, count + 1);
        }

        for(char c : s.toCharArray()) {
            if(reqCount.containsKey(c)) {
                int count = reqCount.get(c);
                filteredS.put(c, count);
            }
        }

        int required = reqCount.size();
        int current = 0;

        int[] res = {-1, -1};
        int resLen = Integer.MAX_VALUE;

        List<Character> filteredSlist = new ArrayList<>(filteredS.keySet());

        int left = 0;
        for(int right=0; right<filteredSlist.size(); right++) {
            char ch = filteredSlist.get(right);
            if(filteredS.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if(window.get(ch) == filteredS.get(ch)) {
                    current++;
                }
            }

            while(current == required) {
                int len = (right - left + 1);
                if(len < resLen) {
                    resLen = len;
                    res[0] = left;
                    res[1] = right;
                }

                char leftChar = filteredSlist.get(left);
                if(window.containsKey(leftChar)) {
                    window.put(leftChar, window.get(leftChar) - 1);
                    if(window.get(leftChar) < filteredS.get(leftChar)) {
                        current--;
                    }
                }
                left++;
            }
        }
        return res[0] == -1 ? "" : s.substring(res[0], res[1] + 1);
    }
}