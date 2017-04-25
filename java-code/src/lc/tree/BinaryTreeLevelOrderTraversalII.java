package lc.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 107. Binary Tree Level Order Traversal II
 * Given a binary lc.tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary lc.tree [3,9,20,null,null,15,7],
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 * return its bottom-up level order traversal as:
 * [
 *  [15,7],
 *  [9,20],
 *  [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int lastNum = 1;
        int currNum = 0;
        List<Integer> levelResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode curr = queue.pop();
            levelResult.add(curr.val);
            lastNum--;

            if (curr.left != null) {
                queue.add(curr.left);
                currNum++;
            }
            if (curr.right != null) {
                queue.add(curr.right);
                currNum++;
            }

            if (lastNum == 0) {
                lastNum = currNum;
                currNum = 0;
                res.add(levelResult);
                levelResult = new ArrayList<>();
            }
        }

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(levelOrderBottom(root));
    }

    private static TreeNode generate() {
        /*
        *       1
        *      / \
        *     2   3
        *    / \
        *   4   5
        *  /
        * 6
        * level order: 1 2 3 4 5 6
        *
        *       1
        *      / \
        *     2
        *    / \
        *   3
        *  /
        * 4
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        //TreeNode t6 = new TreeNode(6);
        //t1.left = t2;
        //t1.right = t3;
        //t2.left = t4;
        //t2.right = t5;
        //t4.left = t6;
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        t4.left = t5;
        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
