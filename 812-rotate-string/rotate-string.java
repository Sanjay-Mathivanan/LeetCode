class Solution {
    public boolean rotateString(String s, String goal) {
        
        // Length must be same
        if (s.length() != goal.length()) {
            return false;
        }

        // Add s with itself
        String doubled = s + s;

        // Check if goal exists in doubled string
        return doubled.contains(goal);
    }
}