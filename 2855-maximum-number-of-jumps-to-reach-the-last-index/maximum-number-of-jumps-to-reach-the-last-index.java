class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        
        int[] dp = new int[n];
        
        // initialize with -1 (unreachable)
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }
        
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            
            // if current index is unreachable
            if (dp[i] == -1) continue;
            
            for (int j = i + 1; j < n; j++) {
                
                if (Math.abs(nums[j] - nums[i]) <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }
        
        return dp[n - 1];
    }
}