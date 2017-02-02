import java.util.Arrays;

/**
 * #34. Search for a Range
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public class SearchForARange {
    public static int[] searchRange(int[] nums, int target) {
        int low = -1;
        int high = -1;
        if (nums == null || nums.length == 0) return new int[]{low,high};

        int l = 0;
        int r = nums.length-1;
        while (l <= r) {
            int m = (l+r)/2;

            if (nums[m] == target) {
                // find low in left half
                low = m;
                while (low>l && nums[low] == nums[low-1]) {
                    low--;
                }

                // find high in right half
                high = m;
                while (high<r && nums[high] == nums[high+1]) {
                    high++;
                }

                return new int[]{low,high};

            } else if (nums[m] < target) {
                l = m+1;
            } else {
                r = m-1;
            }
        }

        return new int[]{low,high};
    }


    // 如果我们不寻找那个元素先，而是直接相等的时候也向一个方向继续夹逼，
    // 如果向右夹逼，最后就会停在右边界，而向左夹逼则会停在左边界，
    // 如此用停下来的两个边界就可以知道结果了，只需要两次二分查找。
    public static int[] searchRange2(int[] nums, int target) {
        int low = -1;
        int high = -1;
        if (nums == null || nums.length == 0) return new int[]{low,high};

        int ll = 0; // ll is the low range
        int lr = nums.length-1;
        while (ll <= lr) {
            int m = (ll+lr)/2;

            if (nums[m] < target) {
                ll = m+1;
            } else { // when finding the low range, even nums[m] equals to target, we are discarding the right half
                lr = m-1;
            }
        }

        int rl = 0;
        int rr = nums.length-1; // rr is the high range
        while (rl <= rr) {
            int m = (rl+rr)/2;

            if (nums[m] <= target) { // when finding the high range, even nums[m] equals to target, we are discarding the right half
                rl = m+1;
            } else {
                rr = m-1;
            }
        }

        if (ll <= rr) {
            low = ll;
            high = rr;
        }

        return new int[]{low,high};
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2,4,5,6};
        System.out.println(Arrays.toString(searchRange(nums,7)));
        System.out.println(Arrays.toString(searchRange2(nums,7)));
    }
}
