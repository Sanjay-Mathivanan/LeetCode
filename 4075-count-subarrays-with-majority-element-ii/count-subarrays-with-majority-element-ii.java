class Solution {

    class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        void update(int idx, int val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        // Prefix sums are in [-n, n]
        int offset = n + 1;
        Fenwick ft = new Fenwick(2 * n + 5);

        long ans = 0;

        int prefix = 0;

        // Empty prefix = 0
        ft.update(offset, 1);

        for (int num : nums) {

            if (num == target)
                prefix++;
            else
                prefix--;

            int index = prefix + offset;

            // Count previous prefix sums < current prefix
            ans += ft.query(index - 1);

            ft.update(index, 1);
        }

        return ans;
    }
}