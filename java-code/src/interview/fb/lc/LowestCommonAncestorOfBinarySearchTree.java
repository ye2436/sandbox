package interview.fb.lc;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree

 Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
  0      _4       7       9
        /  \
       3   5
 For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 */
public class LowestCommonAncestorOfBinarySearchTree {

    // root val is between p & q val, then return root
    // otherwise, if both p, q are smaller than root, look in root.left, otherwise look in root.right

    // recursive
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if ((root.val - p.val) * (root.val - q.val) < 1) return root; // note it's <1 because could be 0, when p or q itself is the LCA
        if (root.val < p.val) return lowestCommonAncestor(root.right, p, q);
        return lowestCommonAncestor(root.left, p, q);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
