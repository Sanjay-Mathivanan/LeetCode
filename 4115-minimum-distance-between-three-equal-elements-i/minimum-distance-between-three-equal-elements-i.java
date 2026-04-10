import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

        // Store indices for each value
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = Integer.MAX_VALUE;

        // Process each value group
        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;

            // Check consecutive triples
            for (int i = 0; i + 2 < list.size(); i++) {
                int left = list.get(i);
                int right = list.get(i + 2);

                int dist = 2 * (right - left);
                ans = Math.min(ans, dist);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}