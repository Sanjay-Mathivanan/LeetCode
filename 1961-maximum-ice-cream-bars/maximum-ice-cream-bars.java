class Solution {
    public int maxIceCream(int[] costs, int coins) {

        int maxCost = 0;

        // Find maximum cost
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        // Frequency array
        int[] freq = new int[maxCost + 1];

        for (int cost : costs) {
            freq[cost]++;
        }

        int bars = 0;

        // Buy cheapest bars first
        for (int price = 1; price <= maxCost; price++) {

            while (freq[price] > 0 && coins >= price) {

                coins -= price;
                bars++;
                freq[price]--;
            }
        }

        return bars;
    }
}