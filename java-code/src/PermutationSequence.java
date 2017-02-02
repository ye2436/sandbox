import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * #60. Permutation Sequence
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
        StringBuilder sb = new StringBuilder();
        List<Integer> nums = new ArrayList<>();
        for (int i=0; i<n; i++) {
            nums.add(i+1);
        }

        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for (int i=1; i<=n; i++) {
            factorial[i] = factorial[i-1]*i;
        }

        k--; // index starts from 0
        int count=n;
        while (count>0) {
            int index = k / factorial[n-1];
            sb.append(nums.get(index));
            nums.remove(index); // O(n)
            k = k % factorial[n-1];
            n--;
            count--;
        }

        return sb.toString();
    }


    // TLE
    // given an ordered array of [1,2,3,…,n], the calculated permutations by using backtracking
    // will always be in ascending order. use k to track count, return only the kth.
    private static void helper(char[] nums, List<Character> currList, int count, List<String> res) {
        if (currList.size() == nums.length) {
            count--;
        }
        if (count == 0) {
            res.add(currList.stream().map(e->e.toString()).collect(Collectors.joining()));
            return;
        } else if (count<0) {
            return;
        }
        for (int i=0; i<nums.length; i++) {
            if (currList.contains(nums[i])) continue;

            currList.add(nums[i]);
            helper(nums, currList, count,res);
            currList.remove(currList.size()-1);
        }
    }


    public static void main(String[] args) {
        System.out.println(getPermutation(4,11));
    }
}
