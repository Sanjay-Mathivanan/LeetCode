class Solution {

    public int maxActiveSectionsAfterTrade(String s) {

        int n = s.length();
        int ones = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";
        int m = t.length();

        int[] start = new int[m];
        int[] end = new int[m];

        // Length of zero block starting at i
        for (int i = m - 2; i >= 0; i--) {
            if (t.charAt(i) == '0')
                start[i] = start[i + 1] + 1;
        }

        // Length of zero block ending at i
        for (int i = 1; i < m; i++) {
            if (t.charAt(i) == '0')
                end[i] = end[i - 1] + 1;
        }

        int best = 0;

        int i = 1;
        while (i < m - 1) {

            if (t.charAt(i) == '1') {

                int j = i;
                while (j < m && t.charAt(j) == '1')
                    j++;

                if (i > 0 && j < m &&
                        t.charAt(i - 1) == '0' &&
                        t.charAt(j) == '0') {

                    int left = end[i - 1];
                    int right = start[j];

                    best = Math.max(best, left + right);
                }

                i = j;
            } else {
                i++;
            }
        }

        return ones + best;
    }
}