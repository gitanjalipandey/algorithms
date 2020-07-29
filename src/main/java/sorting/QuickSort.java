package sorting;

import java.util.Random;

public class QuickSort {
    public int[] sortArray(int[] nums) {
        sort(nums,0,nums.length - 1);
        return nums;
    }

    private void sort(int[] nums , int start , int end){
        if(start < end){
            int pivotIndex = partition(nums,start,end);
            sort(nums,start,pivotIndex-1);
            sort(nums,pivotIndex+1,end);
        }

    }

    private int partition(int[] nums , int start , int end){
        int pivotIndex = new Random().nextInt(end - start) + start;
        int pivot = nums[pivotIndex];
        swap(nums,end,pivotIndex);
        int index = start;
        while(start < end){
            if(nums[start] < pivot){
                swap(nums,index,start);
                index++;
            }
            start++;
        }
        swap(nums,index,end);
        return index;
    }

    private void swap(int[] nums , int i , int j){
        int t = nums[i];
        nums[i] = nums[j] ;
        nums[j] = t;
    }

    public static void main(String[] args){
        QuickSort sort = new QuickSort();
        int[] nums = {9,12,3,4,15};
        sort.sortArray(nums);
        for(int i : nums){
            System.out.println("i="+i);
        }
    }
}
