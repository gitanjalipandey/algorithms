package twopointer;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-k-closest-elements/discuss/202785/Very-simple-Java-O(n)-solution-using-two-pointers

public class FindKClosestElements  {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int start = 0;
        int end = arr.length - 1;
        while(end - start >= k){
            int diff1 = Math.abs(x-arr[start]);
            int diff2 = Math.abs(x-arr[end]);
            if(diff1 > diff2){
                start++;
            }else{
                end--;
            }
        }

        for(int i = start ; i<= end ; i++){
            res.add(arr[i]);
        }

        return res;
    }
}
