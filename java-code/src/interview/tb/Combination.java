package interview.tb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yechen on 5/25/17.
 */
public class Combination {

    // n numbers, to target
    public List<List<Integer>> combinations (int n, int target) {
        return null;
    }

    public void helper(int n, int target, List<Integer> currList, List<List<Integer>> result)  {
        //? check currList size?
        if (target == 0) {
            result.add(new ArrayList<>(currList));
        }
        for (int i=0; i<=target; i++) {

        }
    }
}
