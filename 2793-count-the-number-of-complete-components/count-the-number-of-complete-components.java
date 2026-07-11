class Solution {

    public void dfs(int node, List<Integer>[] adj, boolean[] vis, List<Integer> comp) {
        vis[node] = true;
        comp.add(node);

        for (int nei : adj[node]) {
            if (!vis[nei]) {
                dfs(nei, adj, vis, comp);
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                List<Integer> comp = new ArrayList<>();
                dfs(i, adj, vis, comp);

                int vertices = comp.size();
                int edgeCount = 0;

                for (int node : comp) {
                    edgeCount += adj[node].size();
                }

                edgeCount /= 2;

                if (edgeCount == vertices * (vertices - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }
}