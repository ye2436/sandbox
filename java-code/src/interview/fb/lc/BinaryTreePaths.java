package interview.fb.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths

 Given a binary tree, return all root-to-leaf paths.

 For example, given the following binary tree:

        1
      /   \
     2     3
      \
      5
 All root-to-leaf paths are:

 ["1->2->5", "1->3"]
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, "", res);
        return res;
    }

    // node is never null
    private void helper(TreeNode node, String currPath, List<String> res) {
        if (currPath.length() > 0) {
            currPath += "->";
        }
        currPath += node.val;
        if (node.left == null && node.right == null) { // leaf node
            res.add(currPath);
            return;
        }
        if (node.left != null) {
            helper(node.left, currPath, res);
        }
        if (node.right != null) {
            helper(node.right, currPath, res);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
