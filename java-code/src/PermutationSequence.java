import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 */
public class PermutationSequence {
    public static String getPermutation(int n, int k) {
        // given an ordered array of [1,2,3,…,n], the calculated permutations by using backtracking
        // will always be in ascending order. use k to track count, return only the kth.
        char[] nums = new char[n];
        for (int i=0; i<n; i++) {
            nums[i] = (char) (i+1+'0');
        }
        List<String> res = new ArrayList<>();
        helper(nums, new StringBuilder(), res);
        if (k-1<res.size()) {
            return res.get(k-1);
        }
        return null;
    }

    private static boolean helper(char[] nums, List<Character> currList, int count) {
        if (currList.size() == nums.length) {
            count--;
        }
        if (count == 0) {
            return true;
        }
        for (int i=0; i<nums.length; i++) {
            if (currList.contains(nums[i])) continue;

            currList.add(nums[i]);
            if (helper(nums, currList, count)) {

            }
            currList.remove(currList.size()-1);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(3,5));
    }
}
