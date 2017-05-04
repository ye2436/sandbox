package interview.bb;

import java.util.Arrays;

/**
 * Maximum product of a triplet (subsequnece of size 3) in array
 * Given an integer array, find a maximum product of a triplet in array.
 * Examples:
 * Input:  [10, 3, 5, 6, 20]
 * Output: 1200
 * Multiplication of 10, 6 and 20
 *
 * Input:  [-10, -3, -5, -6, -20]
 * Output: -90
 *
 * Input:  [1, -4, 3, -6, 7, 0]
 * Output: 168
 * http://www.geeksforgeeks.org/find-maximum-product-of-a-triplet-in-array/
 */
public class MaximumProductOfATriplet {

    // Solution 1 - brute force, O(n^3)

    // Solution 2 - keep 4 arrays: leftMax, leftMin, rightMax, rightMin
    // then including nums[i], the other 2 would be in (leftMax, rightMax, leftMin, rightMin)
    // Time: O(n) Space: O(n)
    public int maxProduct(int nums[]) {
        int maxProduct = Integer.MIN_VALUE;
        int n = nums.length;
        int[] leftMax = new int[n];
        leftMax[0] = -1;
        int[] leftMin = new int[n];
        leftMin[0] = -1;
        int[] rightMax = new int[n];
        rightMax[n-1] = -1;
        int[] rightMin = new int[n];
        rightMin[n-1] = -1;

        int lMax = nums[0];
        int lMin = nums[0];
        for (int i=1; i<n; i++) {
            leftMax[i] = lMax;
            lMax = Math.max(lMax, nums[i]);

            leftMin[i] = lMin;
            lMin = Math.min(lMin, nums[i]);
        }

        int rMax = nums[n-1];
        int rMin = nums[n-1];
        for (int i=n-2; i>=0; i--) {
            rightMax[i] = rMax;
            rMax = Math.max(rMax, nums[i]);

            rightMin[i] = rMin;
            rMin = Math.min(rMin, nums[i]);
        }

        for (int i=1; i<n-1; i++) { // exclude 2 ends
            int product1 = Math.max(nums[i] * leftMax[i] * rightMax[i], nums[i] * leftMin[i] * rightMin[i]);
            int product2 = Math.max(nums[i] * leftMax[i] * rightMin[i], nums[i] * rightMax[i] * leftMin[i]);
            maxProduct = Math.max(maxProduct, Math.max(product1, product2));
        }

        return maxProduct;
    }

    // Solution 3 - Sort the array and the max is either the product of the last 3 elements
    // or the product of the first 2 elements and the last
    // Time: O(nlogn) Space:O(1)

    public int maxProduct_2(int nums[]) {
        // throw exception if n<3
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[n-1] * nums[n-2] * nums[n-3], nums[n-1] * nums[0] * nums[1]);
    }

    public static void main(String[] args) {
        MaximumProductOfATriplet solution = new MaximumProductOfATriplet();
        System.out.println(solution.maxProduct(new int[]{10, 3, 5, 6, 20}));
        System.out.println(solution.maxProduct(new int[]{-10, -3, -5, -6, -20}));
        System.out.println(solution.maxProduct(new int[]{1, -4, 3, -6, 7, 0}));
        System.out.println(solution.maxProduct_2(new int[]{10, 3, 5, 6, 20}));
        System.out.println(solution.maxProduct_2(new int[]{-10, -3, -5, -6, -20}));
        System.out.println(solution.maxProduct_2(new int[]{1, -4, 3, -6, 7, 0}));
    }
}
