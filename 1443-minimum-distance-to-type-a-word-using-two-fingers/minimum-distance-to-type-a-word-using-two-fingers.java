import java.util.*;

class Solution {
    public int minimumDistance(String word) {
        int n = word.length();

        // dp[j] = max saving if second finger is at j
        int[] dp = new int[26];

        int total = 0;

        for (int i = 0; i < n - 1; i++) {
            int cur = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';

            int d = dist(cur, next);
            total += d;

            int[] newDp = Arrays.copyOf(dp, 26);

            for (int j = 0; j < 26; j++) {
                // move second finger instead of first
                newDp[cur] = Math.max(
                    newDp[cur],
                    dp[j] + d - dist(j, next)
                );
            }

            dp = newDp;
        }

        int maxSave = 0;
        for (int val : dp) {
            maxSave = Math.max(maxSave, val);
        }

        return total - maxSave;
    }

    // Manhattan distance on keyboard
    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}