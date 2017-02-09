package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * #103. Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *      3
 *     / \
 *    9  20
 *      /  \
 *     15   7
 * return its zigzag level order traversal as:
 * [
 *  [3],
 *  [20,9],
 *  [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    // 偶数层从左到右，奇数层从右到左，每层队列里都要倒序读取，所以改用stack而不是queue
    // 需要两个stack来存节点，因为上下相邻两层的方向不一样
    // 同时维护新旧两个栈，一个来读取，一个存储下一层结点。
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        List<Integer> levelResult;
        stack.push(curr);
        int level = 1;
        while (!stack.isEmpty()) {
            LinkedList<TreeNode> newStack = new LinkedList<>(); // a new level starts
            levelResult = new ArrayList<>();
            while (!stack.isEmpty()) { // loop through nodes from last level
                curr = stack.pop();
                levelResult.add(curr.val);

                if (level%2 == 1) {
                    if (curr.left != null) {
                        newStack.push(curr.left);
                    }
                    if (curr.right != null) {
                        newStack.push(curr.right);
                    }
                } else {
                    if (curr.right != null) {
                        newStack.push(curr.right);
                    }
                    if (curr.left != null) {
                        newStack.push(curr.left);
                    }
                }
            }
            // all nodes from previous level are processed. now add results from this last level to res, and reset values
            if (levelResult.size() > 0) {
                res.add(levelResult);
                level++;
                stack = newStack;
            }
        }

        return res;
    }

    public static List<List<Integer>> zigzagLevelOrder_2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }

    private static void helper(TreeNode curr, int level, List<List<Integer>> res) {
        if (curr == null) return;
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        List<Integer> levelResult = res.get(level);
        if (level%2 == 0) {
            levelResult.add(curr.val);
        } else {
            levelResult.add(0, curr.val); // insert into the first of the list, the rest shifts right.
        }
        helper(curr.left, level+1, res);
        helper(curr.right, level+1, res);
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(zigzagLevelOrder(root));
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
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        //TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.right = t5;
        //t4.left = t6;
        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
