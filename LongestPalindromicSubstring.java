public class LongestPalindromicSubstring {
    // TC: (N*N) where N is length of String
    // SC: (N*N) where N is length of String
    public String longestPalindromeDP(String s) {
        int n = s.length(), start = 0, end = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (j - i < 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] && end - start <= j - i) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    // TC: O(N * N) where N is length of string
    // SC: O(1)
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(i, i, s);
            if (oddLength > end - start + 1) {
                int dist = oddLength / 2;
                start = i - dist;
                end = i + dist;
            }
            int evenLength = expand(i, i + 1, s);
            if (evenLength > end - start + 1) {
                int dist = (evenLength / 2) - 1;
                start = i - dist;
                end = i + dist + 1;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(int i, int j, String s) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}
