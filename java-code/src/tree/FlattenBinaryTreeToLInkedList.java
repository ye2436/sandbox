package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 114. Flatten Binary Tree to Linked List
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *      1
 *     / \
 *    2   5
 *   / \   \
 *  3   4   6
 * The flattened tree should look like:
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 */
public class FlattenBinaryTreeToLInkedList {

    static TreeNode prev = null;

    public static void flatten(TreeNode root) { // recursive
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }


    public static void flatten_iterative(TreeNode root) {
        if (root == null) return;

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null;
        }
    }

    public void flatten_my(TreeNode root) {
        if (root == null) return;

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode last = root;
        if (root.right != null) {
            stack.push(root.right);
        }
        if (root.left != null) {
            stack.push(root.left);
            root.left = null;
        }
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            last.right = curr;
            last = curr;
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
                curr.left = null;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        flatten(root);
        System.out.println(preorder(root));
        System.out.println(inorder(root));
    }
    private static TreeNode generate() {

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t5;
        t2.left = t3;
        t2.right = t4;
        t5.right = t6;
        return t1;
    }

    private static List<Integer> preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.add(root.val);
            res.addAll(preorder(root.left));
            res.addAll(preorder(root.right));
        }
        return res;
    }

    private static List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.addAll(inorder(root.left));
            res.add(root.val);
            res.addAll(inorder(root.right));
        }
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
