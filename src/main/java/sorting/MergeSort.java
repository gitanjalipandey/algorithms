package sorting;

public class MergeSort {
        public int[] sortArray(int[] nums) {
            sort(nums,0,nums.length-1);
            return nums;
        }

        private void sort(int[] nums , int start , int end){
            if(start < end){
                int mid = start + (end - start) / 2;
                sort(nums,start,mid);
                sort(nums,mid+1,end);
                merge(nums,start,mid,end);
            }
        }

        private void merge(int[] nums, int start , int mid , int end){
            int[] arr1 = new int[mid-start+1];
            int[] arr2 = new int[end-mid];

            int index = 0;
            for(int i = start ; i <= mid ; i++){
                arr1[index] = nums[i];
                index++;
            }

            index = 0;
            for(int i = mid+1 ; i <= end ; i++){
                arr2[index] = nums[i];
                index++;
            }

            int i = 0 , j = 0;
            index = start;
            while(i < arr1.length && j < arr2.length){
                if(arr1[i] <= arr2[j]){
                    nums[index] = arr1[i];
                    i++;
                }else{
                    nums[index] = arr2[j];
                    j++;
                }
                index++;
            }

            while(i < arr1.length){
                nums[index] = arr1[i];
                i++;
                index++;
            }

            while(j < arr2.length){
                nums[index] = arr2[j];
                j++;
                index++;
            }
        }

        public static void main(String[] args){
            MergeSort sort = new MergeSort();
            int[] nums = {9,12,3,4,15};
            sort.sortArray(nums);
            for(int i : nums){
                System.out.println("i="+i);
            }

        }
    }

