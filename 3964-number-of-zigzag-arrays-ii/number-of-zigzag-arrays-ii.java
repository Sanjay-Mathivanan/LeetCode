class Solution {

    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;
        int S = 2 * m;

        long[][] T = new long[S][S];

        // index helpers
        // Up(v)   = v-1
        // Down(v) = m + (v-1)

        for (int u = 1; u <= m; u++) {

            for (int v = u + 1; v <= m; v++) {

                int upV = v - 1;
                int downU = m + (u - 1);

                T[upV][downU] = 1;
            }

            for (int v = 1; v < u; v++) {

                int downV = m + (v - 1);
                int upU = u - 1;

                T[downV][upU] = 1;
            }
        }

        long[] base = new long[S];

        for (int v = 1; v <= m; v++) {

            base[v - 1] = v - 1;       // Up(v)

            base[m + v - 1] = m - v;   // Down(v)
        }

        long[][] P = matrixPower(T, n - 2);

        long[] finalVec = multiply(P, base);

        long ans = 0;

        for (long x : finalVec) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] A, long[] v) {

        int n = A.length;

        long[] res = new long[n];

        for (int i = 0; i < n; i++) {

            long sum = 0;

            for (int j = 0; j < n; j++) {

                sum = (sum + A[i][j] * v[j]) % MOD;
            }

            res[i] = sum;
        }

        return res;
    }

    private long[][] matrixPower(long[][] M, long p) {

        int n = M.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (p > 0) {

            if ((p & 1) == 1) {
                res = multiply(res, M);
            }

            M = multiply(M, M);

            p >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {

        int n = A.length;

        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {

            for (int k = 0; k < n; k++) {

                if (A[i][k] == 0) continue;

                long val = A[i][k];

                for (int j = 0; j < n; j++) {

                    if (B[k][j] == 0) continue;

                    C[i][j] =
                        (C[i][j] + val * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }
}