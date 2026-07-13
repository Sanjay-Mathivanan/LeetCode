class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        String digit = "123456789";

        for(int len=2;len<=9;len++){
            for(int start =0;start+len<=9;start++){
                int num = Integer.parseInt(digit.substring(start,start+len));

                if (num >= low && num <= high) {
                    ans.add(num); 

            }
            }
        }
         return ans;
    }
   
}