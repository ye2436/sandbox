package interview.am;

/**
 *  414. Third Maximum Number

 Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

 Example 1:

 Input: [3, 2, 1]

 Output: 1

 Explanation: The third maximum is 1.

 Example 2:

 Input: [1, 2]

 Output: 2

 Explanation: The third maximum does not exist, so the maximum (2) is returned instead.

 Example 3:

 Input: [2, 2, 3, 1]

 Output: 1

 Explanation: Note that the third maximum here means the third maximum distinct number.
 Both numbers with value 2 are both considered as second maximum.

 */
public class ThirdMaximumNumber {

    // use 3 variables
    // * when setting the max values, first move the later(smaller) ones, (from back to front)
    //   e.g. first move max2 to max3, then max1 to max2, then set max1
    public static int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer num : nums) {
            // avoid dup. USE Integer.equals to avoid null pointer
            if (num.equals(max1) || num.equals(max2) ||  num.equals(max3)) continue;

            if (max1 == null || num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (max2 == null || num > max2) {
                max3 = max2;
                max2 = num;
            } else if (max3 == null || num > max3) {
                max3 = num;
            }
        }
        return max3 == null ? max1 : max3;
    }

    public static void main(String[] args) {
        //System.out.println(thirdMax(new int[] {3,2,1}));
        System.out.println(thirdMax(new int[] {2,2,3,1}));
    }
}
