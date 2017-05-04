package interview.bb;

/**
 * Write a function that given a BST, it will return the distance between 2 nodes.
 * For example, given this tree
 *          A
 *         / \
 *        B   C
 *       / \   \
 *      D   E   F
 *     /         \
 *    G           H
 *  The distance between G and E is 3: [G -> D -> B -> E]
 *  The distance between G and H is 6: [G -> D -> B -> A -> C -> F -> H]
 *  http://www.geeksforgeeks.org/find-distance-two-given-nodes/
 */
public class DistanceBetweenTreeNodes {

    // find Lowest Common Ancestor of node1 and node2. and then get distances from lca to node1 and node2
    // add the distances.
    public int findDistance(TreeNode root, TreeNode node1, TreeNode node2) {
        return 0;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
