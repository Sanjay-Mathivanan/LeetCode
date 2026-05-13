class Solution {
    public int minMoves(int[] nums, int limit) {

        int n = nums.length;

        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {

            int a = nums[i];
            int b = nums[n - 1 - i];

            int low = Math.min(a, b);
            int high = Math.max(a, b);

            // initially 2 moves
            diff[2] += 2;

            // one move range starts
            diff[low + 1] -= 1;

            // zero move at exact sum
            diff[a + b] -= 1;

            // after exact sum back to 1 move
            diff[a + b + 1] += 1;

            // after one-move range back to 2 moves
            diff[high + limit + 1] += 1;
        }

        int answer = Integer.MAX_VALUE;
        int current = 0;

        for (int sum = 2; sum <= 2 * limit; sum++) {

            current += diff[sum];

            answer = Math.min(answer, current);
        }

        return answer;
    }
}