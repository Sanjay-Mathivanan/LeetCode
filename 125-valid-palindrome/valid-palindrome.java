class Solution {
    public boolean isPalindrome(String s) {
        String a = s.replaceAll("[\\s\\p{Punct}]","").toLowerCase();
        String rev = new StringBuilder(a).reverse().toString();
        return a.equals(rev);
    }
}

