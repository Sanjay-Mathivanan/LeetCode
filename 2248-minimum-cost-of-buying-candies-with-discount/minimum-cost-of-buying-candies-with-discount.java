import java.util.Arrays;

class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int total = 0;
        int n = cost.length;

        // Traverse from highest cost to lowest
        for (int i = n - 1; i >= 0; i -= 3) {
            total += cost[i]; // most expensive candy

            if (i - 1 >= 0) {
                total += cost[i - 1]; // second most expensive candy
            }
            // i-2 candy is free (if exists)
        }

        return total;
    }
}