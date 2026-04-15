class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int right = (i - startIndex + n) % n;
                int left = (startIndex - i + n) % n;
                ans = Math.min(ans, Math.min(right, left));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}