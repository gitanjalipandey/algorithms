package array;

//https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609705/Java-Simple-O(N-log-N)-Sliding-Window-(TreeSet)

import java.util.Comparator;
import java.util.TreeSet;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public int longestSubarray(int[] nums, int limit) {
        Comparator<Integer> cm = new Comparator<Integer>(){
            public int compare(Integer i1 , Integer i2){
                if(nums[i1] == nums[i2]){
                    return i2 - i1;
                }
                return nums[i1] - nums[i2] ;
            }
        };
        TreeSet<Integer> set = new TreeSet<>(cm);
        int left = 0;
        int right = 1;
        set.add(0);
        int res = 1;
        while(right < nums.length){
            set.add(right);
            // System.out.println("left="+left+" first="+set.first()+ " last="+set.last()+" right="+right);
            while(Math.abs(nums[set.last()] - nums[set.first()]) > limit){
                set.remove(left);
                left++;
            }
            res = Math.max(res,right-left+1);
            right++;
        }
        return res;
    }
}
