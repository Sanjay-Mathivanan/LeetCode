import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;

        // Step 1: Map value -> indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> ans = new ArrayList<>();

        // Step 2: Process queries
        for (int idx : queries) {
            int val = nums[idx];
            List<Integer> list = map.get(val);

            // only one occurrence
            if (list.size() == 1) {
                ans.add(-1);
                continue;
            }

            // binary search to find position
            int pos = Collections.binarySearch(list, idx);

            int res = Integer.MAX_VALUE;

            // next index (circular)
            int next = list.get((pos + 1) % list.size());
            int d1 = Math.abs(next - idx);
            res = Math.min(res, Math.min(d1, n - d1));

            // previous index (circular)
            int prev = list.get((pos - 1 + list.size()) % list.size());
            int d2 = Math.abs(prev - idx);
            res = Math.min(res, Math.min(d2, n - d2));

            ans.add(res);
        }

        return ans;
    }
}