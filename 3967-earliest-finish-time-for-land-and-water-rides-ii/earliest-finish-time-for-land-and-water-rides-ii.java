import java.util.*;

class Solution {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long ans = Long.MAX_VALUE;

        RideHelper water = new RideHelper(waterStartTime, waterDuration);
        RideHelper land = new RideHelper(landStartTime, landDuration);

        // Land -> Water
        for (int i = 0; i < landStartTime.length; i++) {
            long landFinish = (long) landStartTime[i] + landDuration[i];
            ans = Math.min(ans, water.query(landFinish));
        }

        // Water -> Land
        for (int i = 0; i < waterStartTime.length; i++) {
            long waterFinish = (long) waterStartTime[i] + waterDuration[i];
            ans = Math.min(ans, land.query(waterFinish));
        }

        return (int) ans;
    }

    static class RideHelper {

        int n;
        int[] start;
        long[] prefixMinDur;
        long[] suffixMinFinish;

        RideHelper(int[] startTime, int[] duration) {

            n = startTime.length;

            int[][] rides = new int[n][2];

            for (int i = 0; i < n; i++) {
                rides[i][0] = startTime[i];
                rides[i][1] = duration[i];
            }

            Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));

            start = new int[n];
            prefixMinDur = new long[n];
            suffixMinFinish = new long[n];

            for (int i = 0; i < n; i++) {
                start[i] = rides[i][0];
            }

            prefixMinDur[0] = rides[0][1];

            for (int i = 1; i < n; i++) {
                prefixMinDur[i] =
                        Math.min(prefixMinDur[i - 1], rides[i][1]);
            }

            suffixMinFinish[n - 1] =
                    (long) rides[n - 1][0] + rides[n - 1][1];

            for (int i = n - 2; i >= 0; i--) {
                long value = (long) rides[i][0] + rides[i][1];

                suffixMinFinish[i] =
                        Math.min(suffixMinFinish[i + 1], value);
            }
        }

        long query(long t) {

            int idx = lowerBound(start, (int) t);

            long ans = Long.MAX_VALUE;

            // rides with start < t
            if (idx > 0) {
                ans = Math.min(ans, t + prefixMinDur[idx - 1]);
            }

            // rides with start >= t
            if (idx < n) {
                ans = Math.min(ans, suffixMinFinish[idx]);
            }

            return ans;
        }

        private int lowerBound(int[] arr, int target) {

            int left = 0;
            int right = arr.length;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (arr[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }
}