package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * #1. Two Sum
 */
public class
TwoSum {
    public int[] solution_1(int[] nums, int target) {
        int index1 = 0;
        int index2 = 0;

        for (int j=0; j < nums.length-1; j++) {
            index1 = j;
            for (int i=j+1; i < nums.length; i++) {
                if (nums[index1] + nums[i] == target) {
                    index2 = i;
                    return new int[]{index1+1, index2+1};
                }
                continue;
            }
        }

        return new int[]{index1, index2};
    }

    public int[] solution_2(int[] nums, int target) throws Exception {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<nums.length-1; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new Exception("No solution found");
    }
}
