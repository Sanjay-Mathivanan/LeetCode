class Solution {
    public boolean isGood(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length - 1;

        // check 1 to n-1
        for (int i = 0; i < n; i++) {

            if (nums[i] != i + 1) {
                return false;
            }
        }

        // last element should also be n
        return nums[n] == n;
    }
}