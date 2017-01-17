import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        if(nums == null || nums.length<2) return;

        // scan from right to left, find the first element that is less than its previous one
        int p=0;
        for (int i=nums.length-1; i>0; i--) {
            if (nums[i-1] < nums[i]) {
                p = i-1;
                break;
            }
        }

        // scan from right to left, find the first element that is greater than p
        int q=0;
        for (int i=nums.length-1; i>p; i--) {
            if (nums[i] > nums[p]) {
                q = i;
                break;
            }
        }

        if(p==0 && q==0){
            // reverse from 0 to nums.length
            reverse(nums, 0, nums.length);
            return;
        }

        // swap p and q
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;

        // reverse from p+1 to nums.length
        reverse(nums,p+1,nums.length);
    }

    public static void reverse(int[] nums, int start, int end) { // start inclusive, end exclusive
        int length = end - start;
        for (int i=0; i<length/2; i++)  {
            int temp = nums[start+i];
            nums[start+i] = nums[end-1-i];
            nums[end-1-i] = temp;
        }
    }

    public void reverse2(int[] nums, int left, int right){
        while(left<right){
            int temp = nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4,3,5,7,6,2,1};
        int[] nums2 = new int[]{4,3,2,1};
        nextPermutation(nums2);
        System.out.println(nums2);
    }
}
