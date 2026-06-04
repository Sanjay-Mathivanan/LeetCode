class Solution {

    public int totalWaviness(int num1, int num2) {

        int ans = 0;

        for (int num = num1; num <= num2; num++) {
            ans += getWaviness(num);
        }

        return ans;
    }

    private int getWaviness(int num) {

        String s = String.valueOf(num);

        if (s.length() < 3) {
            return 0;
        }

        int waviness = 0;

        for (int i = 1; i < s.length() - 1; i++) {

            char prev = s.charAt(i - 1);
            char cur = s.charAt(i);
            char next = s.charAt(i + 1);

            if ((cur > prev && cur > next) ||
                (cur < prev && cur < next)) {

                waviness++;
            }
        }

        return waviness;
    }
}