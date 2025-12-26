package org.dsa;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] s = new String[] {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(s));
    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        String prefix = strs[0];
        for(int i=1; i<strs.length; i++) {
            String s = strs[i];
            while(s.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

}
