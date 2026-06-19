class Solution {
    public int largestAltitude(int[] gain) {

        int currentAltitude = 0;
        int highestAltitude = 0;

        for (int i = 0; i < gain.length; i++) {

            currentAltitude = currentAltitude + gain[i];

            if (currentAltitude > highestAltitude) {
                highestAltitude = currentAltitude;
            }
        }

        return highestAltitude;
    }
}