package interview.li;

import java.util.ArrayList;
import java.util.List;

/**
 * 515. Find Largest Value in Each Tree Row
 */
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }

    public void helper(TreeNode node, int depth, List<Integer> res) {
        if (node == null) return;
        if (res.size() == depth) {
            res.add(Integer.MIN_VALUE);
        }
        res.set(depth, Math.max(res.get(depth), node.val));
        helper(node.left, depth+1, res);
        helper(node.right, depth+1, res);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
