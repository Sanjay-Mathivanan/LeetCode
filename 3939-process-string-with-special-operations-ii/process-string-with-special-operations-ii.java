class Solution {
    public char processStr(String s, long k) {

        int n = s.length();
        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            len[i + 1] = len[i];

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1]++;
            } else if (ch == '*') {
                if (len[i + 1] > 0)
                    len[i + 1]--;
            } else if (ch == '#') {
                len[i + 1] *= 2;
            }
        }

        if (k >= len[n])
            return '.';

        long curLen = len[n];

        for (int i = n - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {

                if (k == curLen - 1)
                    return ch;

                curLen--;

            } else if (ch == '*') {

                curLen++;

            } else if (ch == '#') {

                long half = curLen / 2;

                if (k >= half)
                    k -= half;

                curLen = half;

            } else if (ch == '%') {

                k = curLen - 1 - k;

            }
        }

        return '.';
    }
}