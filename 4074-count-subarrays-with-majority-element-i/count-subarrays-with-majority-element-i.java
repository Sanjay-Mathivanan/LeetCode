class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;
        int ans = 0;

        for (int start = 0; start < n; start++) {

            int countTarget = 0;

            for (int end = start; end < n; end++) {

                if (nums[end] == target) {
                    countTarget++;
                }

                int len = end - start + 1;

                if (countTarget > len / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }
}