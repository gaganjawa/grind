package org.dsa;

import java.util.*;

public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int windowStart = 0;
        int windowLength = 0;
        int longest = 0;
        int i=0;

        for(i=0; i<s.length(); i++) {

            if (charIndexMap.containsKey(s.charAt(i)) && charIndexMap.get(s.charAt(i)) >= windowStart) {
                windowStart = charIndexMap.get(s.charAt(i)) + 1;
            }
            charIndexMap.put(s.charAt(i), i);
            longest = Math.max(longest, i - windowStart + 1);
        }

        longest = Math.max(longest, i - windowStart);
        return longest;

    }
}
