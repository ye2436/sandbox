/**
 * #137. Single Number II
 * Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public class SingleNumberII {
    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] counts = new int[32];
        for (int i=0; i<32; i++) { // sum up 1's at each bit. 0是低位（最右），31是高位（最左）
            for (int num : nums) {
                // to get bit at idx i - shift right i times, the bit we are trying to get is now the lowest order bit.
                // use &1 to get the lowest order bit.
                counts[i] += (num >> i) & 1;
            }
        }
        int res = 0;
        for (int i=0; i<32; i++) {
            res += (counts[i]%3) << i;
        }
        return res;
    }

    public static void main (String[] args) {
        int[] nums = {1,3,4,2,2,3,4,4,3,2};
        System.out.println(singleNumber(nums));
    }
}
