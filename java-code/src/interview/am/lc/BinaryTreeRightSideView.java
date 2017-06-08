package interview.am.lc;

import java.util.ArrayList;
import java.util.List;

/**
 *  199. Binary Tree Right Side View

 Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

 For example:
 Given the following binary tree,

        1            <---
      /   \
     2     3         <---
      \     \
       5     4       <---

 You should return [1, 3, 4].
 */
public class BinaryTreeRightSideView {

    // find the right most node of each level
    // dfs - check node, right, and then left.
    // add a node val to the list only if this level does not already have a value
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        dfs(root, 0, results);
        return results;
    }

    private void dfs(TreeNode node, int level, List<Integer> results) {
        if (node == null) return;
        if (results.size() == level) {
            results.add(node.val);
        }
        dfs(node.right, level+1, results);
        dfs(node.left, level+1, results);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
