class Solution {
    public int maxArea(int[] height) {

        int left =0;
        int right = height.length-1;
        int maxA =0;

        while(left < right){

            int width = right-left;
            int h = Math.min(height[left],height[right]);
            maxA = Math.max(maxA, width*h);

            if(height[left]<height[right]){
                left++;
            }else{
                right--;
                        
            }


        }
        return maxA;
        
    }
}