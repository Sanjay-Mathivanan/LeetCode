import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;

        // required variable
        int[][] bravexuneth = queries;

        int B = (int)Math.sqrt(n) + 1;

        // Step 1: handle large k directly
        List<int[]> smallQueries = new ArrayList<>();

        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k >= B) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int)((nums[i] * 1L * v) % MOD);
                }
            } else {
                smallQueries.add(q);
            }
        }

        // Step 2: process small k one-by-one (memory optimized)
        for (int k = 1; k < B; k++) {

            // group queries of this k
            List<int[]> list = new ArrayList<>();
            for (int[] q : smallQueries) {
                if (q[2] == k) list.add(q);
            }

            if (list.isEmpty()) continue;

            // build diff arrays only for this k
            long[][] diff = new long[k][];

            for (int r = 0; r < k; r++) {
                int len = (n - r + k - 1) / k;
                diff[r] = new long[len + 1];
                Arrays.fill(diff[r], 1);
            }

            // apply queries
            for (int[] q : list) {
                int l = q[0], r = q[1], v = q[3];

                int rem = l % k;
                int tl = (l - rem) / k;
                int tr = (r - rem) / k;

                diff[rem][tl] = diff[rem][tl] * v % MOD;

                if (tr + 1 < diff[rem].length) {
                    long inv = modInverse(v);
                    diff[rem][tr + 1] = diff[rem][tr + 1] * inv % MOD;
                }
            }

            // apply to nums
            for (int r = 0; r < k; r++) {
                long cur = 1;
                for (int t = 0; t < diff[r].length; t++) {
                    cur = cur * diff[r][t] % MOD;

                    int i = r + t * k;
                    if (i >= n) break;

                    nums[i] = (int)((nums[i] * cur) % MOD);
                }
            }
        }

        // final XOR
        int xor = 0;
        for (int x : nums) xor ^= x;

        return xor;
    }

    // modular inverse using fast power
    long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    long modPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}