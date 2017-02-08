package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #99. Recover Binary Search Tree
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {
    // 1 6 3 4 5 2 - 2 & 6 swapped.
    // first (6, 3) - store the max and the min of the 2
    // then  (5, 2) - store the max and the min of the 2
    // after traversal, swap the max and the min
    // the anomaly pair could appear only once (when the swapped ones are adjacent), e.g. (3, 2)
    public static void recoverTree(TreeNode root) {
        if (root == null) return;

        TreeNode  temp = root;
        List<TreeNode> pre = new ArrayList<>();
        pre.add(null);
        List<TreeNode> swapped = new ArrayList<>();
        swapped.add(null); // index 0: min
        swapped.add(null); // index 1: max
        helper(temp, pre, swapped);

        // swap min and max
        if (swapped.get(0) != null && swapped.get(1) != null) {
            int tempVal = swapped.get(0).val;
            swapped.get(0).val = swapped.get(1).val;
            swapped.get(1).val = tempVal;
        }

    }

    private static void helper(TreeNode root, List<TreeNode> pre, List<TreeNode> swapped) {
        if (root == null) return;
        helper(root.left, pre, swapped);
        if (pre.get(0) != null && pre.get(0).val >= root.val) {
            if (swapped.get(0) == null || root.val < swapped.get(0).val) {
                swapped.set(0, root); // min
            }
            if (swapped.get(1) == null || pre.get(0).val > swapped.get(1).val) {
                swapped.set(1, pre.get(0)); // max
            }
        }
        pre.set(0, root);
        helper(root.right, pre, swapped);
    }

    public static void recoverTree_2(TreeNode root) {
        if (root == null) return;

        TreeNode  temp = root;
        List<TreeNode> pre = new ArrayList<>();
        pre.add(null);
        List<TreeNode> swapped = new ArrayList<>();
        helper_2(temp, pre, swapped);

        // swap back
        if (swapped.size() > 0) {
            int tempVal = swapped.get(0).val;
            swapped.get(0).val = swapped.get(1).val;
            swapped.get(1).val = tempVal;
        }

    }

    // 中序遍历寻找逆序情况，调换的第一个元素，永远是第一个逆序的第一个元素，而调换的第二个元素如果只有一次逆序，则是那一次的后一个，如果有两次逆序则是第二次的后一个
    private static void helper_2(TreeNode root, List<TreeNode> pre, List<TreeNode> swapped) {
        if (root == null) return;
        helper_2(root.left, pre, swapped);
        if (pre.get(0) != null && pre.get(0).val >= root.val) {
            if (swapped.size() == 0) { // 第一个逆序对出现，比如说（6，3），将两个都加入list
                swapped.add(pre.get(0));
                swapped.add(root);
            } else { // 第二个逆序对出现，比如说（5，2），list中的后一个元素替换为逆序对中的第二个元素
                swapped.set(1, root);
            }
        }
        pre.set(0, root);
        helper_2(root.right, pre, swapped);
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(inorder(root));
        recoverTree_2(root);
        System.out.println(inorder(root));
    }

    private static TreeNode generate() {
        /*
        * Original:
        *       4
        *      / \
        *     2   5
        *    / \
        *   1   3
        * Inorder: 1 2 3 4 5
        *
        * Swapped: 3 <-> 5
        *       4
        *      / \
        *     2   3
        *    / \
        *   1   5
        * Inorder : 1 2 5 4 3
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t4.left = t2;
        t4.right = t3;
        t2.left = t1;
        t2.right = t5;
        return t4;
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
