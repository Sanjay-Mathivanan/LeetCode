class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        // Count characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        char[] result = new char[target.length()];

        // Try to build a permutation equal to target
        for (int i = 0; i < target.length(); i++) {

            int current = target.charAt(i) - 'a';

            // Try to put the same character
            if (freq[current] > 0) {
                result[i] = target.charAt(i);
                freq[current]--;
                continue;
            }

            // Same character is unavailable.
            // Find the smallest larger character.
            for (int c = current + 1; c < 26; c++) {

                if (freq[c] > 0) {
                    result[i] = (char) ('a' + c);
                    freq[c]--;

                    // Fill remaining positions with smallest characters
                    int pos = i + 1;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            result[pos++] = (char) ('a' + x);
                            freq[x]--;
                        }
                    }

                    return new String(result);
                }
            }

            // Need to backtrack
            // Restore previous characters
            for (int j = i - 1; j >= 0; j--) {
                int prev = result[j] - 'a';
                freq[prev]++;

                int targetChar = target.charAt(j) - 'a';

                // Find smallest character greater than target[j]
                for (int c = targetChar + 1; c < 26; c++) {

                    if (freq[c] > 0) {

                        result[j] = (char) ('a' + c);
                        freq[c]--;

                        int pos = j + 1;

                        for (int x = 0; x < 26; x++) {
                            while (freq[x] > 0) {
                                result[pos++] = (char) ('a' + x);
                                freq[x]--;
                            }
                        }

                        return new String(result);
                    }
                }
            }

            return "";
        }

        // s itself can form exactly target, but we need strictly greater
        for (int j = target.length() - 1; j >= 0; j--) {

            int prev = result[j] - 'a';
            freq[prev]++;

            int targetChar = target.charAt(j) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    result[j] = (char) ('a' + c);
                    freq[c]--;

                    int pos = j + 1;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            result[pos++] = (char) ('a' + x);
                            freq[x]--;
                        }
                    }

                    return new String(result);
                }
            }
        }

        return "";
    }
}