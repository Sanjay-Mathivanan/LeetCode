class Solution {
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            // minimum is on right side
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }

            // minimum is at mid or left side
            else {
                right = mid;
            }
        }

        return nums[left];
    }
}