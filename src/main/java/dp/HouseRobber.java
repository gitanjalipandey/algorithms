package dp;

import java.util.Arrays;

// https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
public class HouseRobber {
    public int rob(int[] nums) {
        int end = nums.length - 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp , -1);
        dp[0] = nums[0];
        dfs(nums,dp,end);
        return dp[end];
    }

    private int dfs(int[] nums , int[] dp , int index){
        if(index < 0) return 0;
        if(dp[index] >= 0) return dp[index];
        dp[index] = Math.max(nums[index] + dfs(nums,dp,index-2) , dfs(nums,dp,index-1));
        return dp[index];
    }

    public static void main(String[] args){
        HouseRobber robber = new HouseRobber();
        int res = robber.rob(new int[]{2,7,9,3,1});
        System.out.println("res="+res);
    }
}
