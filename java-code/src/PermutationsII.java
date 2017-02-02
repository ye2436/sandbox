import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #47. Permutations II
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 *  [1,1,2],
 *  [1,2,1],
 *  [2,1,1]
 *  ]
 */
public class PermutationsII {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        Arrays.sort(nums);
        helper(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }

    private static void helper(int[] nums, boolean[] used, List<Integer> currList, List<List<Integer>> res) {
        if (currList.size() == nums.length) {
            res.add(new ArrayList<>(currList));
            return;
        }
        for (int i=0; i<nums.length; i++) {
            if (i>0 && !used[i-1] && nums[i] == nums[i-1]) {
                continue;
            }
            if(!used[i]) {
                currList.add(nums[i]);
                used[i] = true;
                helper(nums,used, currList, res);
                currList.remove(currList.size()-1);
                used[i] = false;
            }

        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};

        System.out.println(permute(nums));
    }
}
