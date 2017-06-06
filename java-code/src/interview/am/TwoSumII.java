package interview.am;

/**
 * 167. Two Sum II - Input array is sorted

 Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution and you may not use the same element twice.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2
 */
public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[] {0,0};
        int l = 0;
        int r = numbers.length-1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[] {l+1, r+1};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[] {0,0};
    }
}
