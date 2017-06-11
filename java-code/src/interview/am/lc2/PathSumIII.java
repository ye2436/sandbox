package interview.am.lc2;

import java.util.HashMap;
import java.util.Map;

/**
 *  437. Path Sum III

 You are given a binary tree in which each node contains an integer value.

 Find the number of paths that sum to a given value.

 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

            10
           /  \
          5   -3
         / \    \
        3   2   11
       / \   \
      3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11

 */
public class PathSumIII {

    // Solution 1:
    // brute force recursive. pathSum(root) = pathSum(left)+pathSum(right) + number of paths starting from root.
    // number of paths starting from root = PathSumII(but ending point doesn't have to be leaf, and we need count only)
    // Time Complexity: each node are accessed by average logn times. So O(nlogn)
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return dfs(root, sum)+pathSum(root.left, sum)+pathSum(root.right, sum);
    }

    private int dfs(TreeNode root, int sum){ // number of paths starting from root
        int res = 0;
        if(root == null)
            return res;
        if(sum == root.val)
            res++;
        res+=dfs(root.left,sum - root.val);
        res+=dfs(root.right,sum - root.val);
        return res;
    }


    // Solution 2: Prefix sum method (or cumulative sum)
    // Time: O(n)
    public static int pathSum2(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }

    public static int helper(TreeNode root, int currSum, int target, Map<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(pathSum2(buildTree(), 8));
    }

    private static TreeNode buildTree() {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(-3);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(11);
        TreeNode t7 = new TreeNode(3);
        TreeNode t8 = new TreeNode(-1);
        TreeNode t9 = new TreeNode(1);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;
        t4.left = t7;
        t4.right = t8;
        t5.right = t9;

        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
