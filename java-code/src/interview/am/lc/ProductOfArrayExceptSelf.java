package interview.am.lc;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product
 * of all the elements of nums except nums[i].
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * Follow up:
 * Could you solve it with constant space complexity?
 * (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

    // Solution 1 - If division is allowed, loop through array and get the product of all numbers.
    // Then loop thru array again and divide the product by each number


    // Solution 2 - For each number, the product has 2 portions. One portion is the product of numbers before it
    // and the other is the product of numbers after it.
    // So we can keep 2 arrays, representing the product of numbers before and after respectively. (loop twice)
    // And loop once more, times the 2 arrays.
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] befores = new int[nums.length];
        int[] afters = new int[nums.length];
        int[] products = new int[nums.length];

        befores[0] = 1;
        for (int i=1; i<nums.length; i++) {
            befores[i] = nums[i-1] * befores[i-1];
        }
        afters[nums.length-1] = 1;
        for (int i=nums.length-2; i>=0; i--) {
            afters[i] = afters[i+1] * nums[i+1];
        }

        for (int i=0; i<nums.length; i++) {
            products[i] = befores[i] * afters[i];
        }

        return products;
    }

    // Without extra space:
    // Same idea as solution 2, but put intermediate results in the return array, and accumulate results.
    public int[] productExceptSelf_2(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int[] products = new int[nums.length];

        products[0] = 1;
        for (int i=1; i<nums.length; i++) {
            products[i] = nums[i-1] * products[i-1];
        } // now products contains product on the left side of i

        int right = 1; // running product of numbers on the right side of i
        for (int i=nums.length-1; i>=0; i--) {
            products[i] *= right; // product = left * right
            right *= nums[i];
        }
        return products;
    }
}
