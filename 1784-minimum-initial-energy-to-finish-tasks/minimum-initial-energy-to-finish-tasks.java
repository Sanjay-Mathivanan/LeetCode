class Solution {
    public int minimumEffort(int[][] tasks) {

        Arrays.sort(tasks, (a, b) ->
            (b[1] - b[0]) - (a[1] - a[0])
        );

        int energy = 0;
        int answer = 0;

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            // add extra energy if needed
            if (energy < minimum) {
                answer += (minimum - energy);
                energy = minimum;
            }

            // complete task
            energy -= actual;
        }

        return answer;
    }
}