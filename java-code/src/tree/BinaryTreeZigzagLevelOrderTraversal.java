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
        List<Integer> levelResult = new ArrayList<>();
        stack.add(curr);
        while (!stack.isEmpty()) {

        }

        return res;
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
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t4.left = t6;
        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
