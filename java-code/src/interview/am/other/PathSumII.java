package interview.am.other;

import java.util.ArrayList;
import java.util.List;

/**
 * #113. Path Sum II
 * Given a binary lc.tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary lc.tree and sum = 22,
 *          5
 *         / \
 *        4   8
 *       /   / \
 *      11  13  4
 *     /  \    / \
 *    7    2  5   1
 * return
 * [
 *  [5,4,11,2],
 *  [5,8,4,5]
 * ]
 */
public class PathSumII {

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        helper(root, sum, new ArrayList<>(), results);
        return results;
    }

    private static void helper(TreeNode root, int sum, List<Integer> currList, List<List<Integer>> results) {
        if (root == null) return;
        currList.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            results.add(new ArrayList<>(currList));
            currList.remove(currList.size()-1);
            return;
        }
        helper(root.left, sum-root.val, currList, results);
        helper(root.right, sum-root.val, currList, results);
        currList.remove(currList.size()-1);
    }

    // my solution
    public static List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper_2(root, sum, new ArrayList<>(), res);
        return res;
    }

    private static void helper_2(TreeNode root, int sum, List<Integer> currList, List<List<Integer>> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                currList.add(root.val);
                res.add(new ArrayList<>(currList));
                currList.remove(currList.size()-1);
            }
            return;
        }

        currList.add(root.val);
        helper_2(root.left, sum-root.val, currList, res);
        helper_2(root.right, sum-root.val, currList, res);
        currList.remove(currList.size()-1);
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(pathSum(root, 22));
        System.out.println(pathSum2(root, 22));
    }

    private static TreeNode generate() {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(8);
        TreeNode t4 = new TreeNode(11);
        TreeNode t5 = new TreeNode(13);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(2);
        TreeNode t9 = new TreeNode(5);
        TreeNode t10 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.left = t5;
        t3.right = t6;
        t4.left = t7;
        t4.right = t8;
        t6.left = t9;
        t6.right = t10;
        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
