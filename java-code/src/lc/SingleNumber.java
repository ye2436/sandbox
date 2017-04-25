package lc;

import java.util.HashSet;
import java.util.Set;

/**
 * #136. Single Number
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumber {
    public static int singleNumber_withExtraMemory(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        for (int key : set) {
            return key;
        }

        return 0;
    }

    // bit manipulation
    // - same number XOR itself = 0
    // - any number XOR 0 = itself
    // therefore XOR all the numbers, any number appears twice in the array will be cancelled off.
    // the single number will remain in the result.
    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;

        for (int num : nums) {
            res ^= num;
        }

        return res;
    }

    public static void main (String[] args) {
        // same number XOR it self = 0
        // any number XOR 0 = itself
        System.out.println(3^3);
        System.out.println(3^3^5);

        int[] nums = {1,3,4,2,6,3,2,5,6,4,1};
        System.out.println(singleNumber_withExtraMemory(nums));
        System.out.println(singleNumber(nums));

    }
}
