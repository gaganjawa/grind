package org.dsa.dynamicprogramming;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        int n = 5 , m = 6;
        String s1 = "ababa";
        String s2 = "cbbcad";

        System.out.println(findLCS(n, m, s1, s2));
    }

    public static String findLCS(int n, int m, String s1, String s2) {

        int[][] dp = new int[n + 1][m + 1];
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int len = dp[n][m];
        int idx = len - 1;
        int i = n, j = m;
        char[] arr = new char[len];
        Arrays.fill(arr, '$');

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                arr[idx] = s1.charAt(i - 1);
                i--;
                j--;
                idx--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }

        String ans = new String(arr);

        return ans;
    }

}
