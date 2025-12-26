package org.dsa;

public class MergeAlternately {

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();

        int i = 0, j = 0;
        sb.append(word1.charAt(i++));
        while(i < word1.length() && j < word2.length()) {
            if (j<i) {
                sb.append(word1.charAt(j++));
            } else {
                sb.append(word1.charAt(i++));
            }
        }

        while (i < word1.length()) {
            sb.append(word1.charAt(i++));
        }

        while (j < word2.length()) {
            sb.append(word2.charAt(j++));
        }

        return sb.toString();
    }


}
