import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] pos = new long[n];

        // Step 1: Map to perimeter
        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];

            if (y == 0) pos[i] = x;
            else if (x == side) pos[i] = side + y;
            else if (y == side) pos[i] = 2L * side + (side - x);
            else pos[i] = 3L * side + (side - y);
        }

        Arrays.sort(pos);

        long perimeter = 4L * side;

        // Step 2: Extend array
        long[] ext = new long[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = pos[i];
            ext[i + n] = pos[i] + perimeter;
        }

        long low = 0, high = perimeter, ans = 0;

        // Step 3: Binary search
        while (low <= high) {
            long mid = (low + high) / 2;

            if (canPick(ext, pos, k, mid, perimeter)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return (int) ans;
    }

    // Optimized check using binary search jumps
    private boolean canPick(long[] ext, long[] pos, int k, long d, long perimeter) {
        int n = pos.length;

        for (int start = 0; start < n; start++) {
            int count = 1;
            long first = ext[start];
            long last = first;

            int idx = start;

            while (count < k) {
                long target = last + d;

                // binary search for next valid point
                int next = lowerBound(ext, target, idx + 1, start + n);
                if (next == start + n) break;

                last = ext[next];
                idx = next;
                count++;
            }

            if (count == k) {
                // circular check
                if (perimeter - (last - first) >= d) {
                    return true;
                }
            }
        }

        return false;
    }

    // Standard lower_bound
    private int lowerBound(long[] arr, long target, int l, int r) {
        int ans = r;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}