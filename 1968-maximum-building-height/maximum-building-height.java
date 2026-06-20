import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {

        List<int[]> list = new ArrayList<>();

        // Building 1 always has height 0
        list.add(new int[]{1, 0});

        for (int[] r : restrictions) {
            list.add(new int[]{r[0], r[1]});
        }

        // Building n
        list.add(new int[]{n, n - 1});

        // Sort by building id
        Collections.sort(list, (a, b) -> a[0] - b[0]);

        // Left to Right pass
        for (int i = 1; i < list.size(); i++) {
            int dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(list.get(i)[1],
                    list.get(i - 1)[1] + dist);
        }

        // Right to Left pass
        for (int i = list.size() - 2; i >= 0; i--) {
            int dist = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(list.get(i)[1],
                    list.get(i + 1)[1] + dist);
        }

        int ans = 0;

        // Find maximum peak
        for (int i = 1; i < list.size(); i++) {

            int x1 = list.get(i - 1)[0];
            int h1 = list.get(i - 1)[1];

            int x2 = list.get(i)[0];
            int h2 = list.get(i)[1];

            int dist = x2 - x1;

            int peak = (h1 + h2 + dist) / 2;

            ans = Math.max(ans, peak);
        }

        return ans;
    }
}