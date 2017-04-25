package lc.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #129. Sum Root to Leaf Numbers
 * Given a binary lc.tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *      1
 *     / \
 *    2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 */
public class SumRootToLeafNumbers {
    public static int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        List<Integer> numbers = new ArrayList<>();
        helper(root, path, numbers);
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return sum;
    }

    // path: the number represented by the path from root to node, not including node's leaves yet
    private static void helper(TreeNode node, List<Integer> path, List<Integer> numbers) {
        if (node.left == null && node.right == null) {
            int number = 0;
            for (int i=0; i<path.size(); i++) {
                number = number * 10 + path.get(i);
            }
            numbers.add(number);
            return;
        }
        if (node.left != null) {
            path.add(node.left.val);
            helper(node.left, path, numbers);
            path.remove(path.size()-1);
        }
        if (node.right != null) {
            path.add(node.right.val);
            helper(node.right, path, numbers);
            path.remove(path.size()-1);
        }
    }

    public static int sumNumbers_recursive(TreeNode root) {
        return sum(root, 0);
    }

    // s: sum before node
    // returns the sum from node to bottom
    private static int sum(TreeNode node, int s) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return s*10 + node.val;
        }
        return sum(node.left, s*10+node.val) + sum(node.right, s*10+node.val);
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(sumNumbers(root));
        System.out.println(sumNumbers_recursive(root));
    }

    private static TreeNode generate() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
