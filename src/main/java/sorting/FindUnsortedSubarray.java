package sorting;

public class FindUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min  = Integer.MAX_VALUE;
        int end = -1;
        int start = -1;

        for(int i = 0 ; i < nums.length ; i++){
            max = Math.max(max,nums[i]);
            if(nums[i] < max){
                end = i;
            }
        }

        if(end == -1) return 0;

        for(int i = nums.length - 1; i >= 0 ; i--){
            min = Math.min(min, nums[i]);
            if(nums[i] > min){
                start = i;
            }
        }

        return end - start + 1;
    }

    public static void main(String[] args){
        FindUnsortedSubarray findUnsortedSubarray = new FindUnsortedSubarray();
        int[] nums = {2,6,4,8,10,9,15};
        System.out.println("length="+findUnsortedSubarray.findUnsortedSubarray(nums));
    }
}
