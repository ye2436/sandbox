package interview.am;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  538. Convert BST to Greater Tree

 Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

 Example:

 Input: The root of a Binary Search Tree like this:
        5
      /   \
    2     13

 Output: The root of a Greater Tree like this:
        18
      /   \
    20     13

 */
public class ConvertBSTtoGreaterTree {

    // Do a reversed in-order traversal (right, node, left)
    // Accumulate prev value to curr value. The right most node unchanged.
    public TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        Integer sum = null;
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.right;
            } else { // reached right most
                curr = stack.pop();
                if (sum != null) {
                    curr.val += sum;
                }
                sum = curr.val;
                curr = curr.left;
            }
        }
        return root;
    }

    // recursive version
    public TreeNode convertBST_recursive(TreeNode root) {
        inorder(root, new ArrayList<>());
        return root;
    }

    public void inorder(TreeNode node, List<Integer> sum) {
        if (node == null) return;
        inorder(node.right, sum);
        if (sum.size() > 0) {
            node.val += sum.get(0);
            sum.set(0, node.val);
        } else {
            sum.add(node.val);
        }
        inorder(node.left, sum);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
