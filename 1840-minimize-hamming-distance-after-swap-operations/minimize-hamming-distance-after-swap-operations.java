import java.util.*;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        // Step 1: Union-Find
        UnionFind uf = new UnionFind(n);
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        // Step 2: Group indices
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        int result = 0;

        // Step 3: Process each group
        for (List<Integer> group : groups.values()) {
            Map<Integer, Integer> freq = new HashMap<>();

            // Count source values
            for (int idx : group) {
                freq.put(source[idx], freq.getOrDefault(source[idx], 0) + 1);
            }

            // Match with target
            for (int idx : group) {
                int val = target[idx];
                if (freq.getOrDefault(val, 0) > 0) {
                    freq.put(val, freq.get(val) - 1);
                } else {
                    result++; // mismatch
                }
            }
        }

        return result;
    }
}

// Union-Find class
class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB)
            parent[rootA] = rootB;
    }
}