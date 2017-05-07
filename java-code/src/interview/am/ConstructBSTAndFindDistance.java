package interview.am;

import java.util.*;

/**
 * Given a list of unique integers, construct the binary tree by given order without re-balancing,
 * then find out the distance between two nodes.
 *
 * for example,
 * values= [5,6,3,1,2,4], n is the size of values, node1 is 2, node2 is 4, then function return 3
 *
 * 构建完BST如下，2和4呢，距离就是3
 *          5
 *        /  \
 *      3     6
 *     / \
 *    1   4
 *     \
 *     2
 */
public class ConstructBSTAndFindDistance {
    public int bstDistance(int[] values, int n, int node1, int node2) {

        // 1. construct bst
        TreeNode root = new TreeNode(values[0]);
        for (int i=1; i<n; i++) {
            root = insertNode(root, new TreeNode(values[i]));
        }

        // 2. find LCA of node1 and node2
        int lca = getLowestCommonAncestor(root, node1, node2);

        // 3. get distance
        // distance(n1, n2) = distance(root, n1) + distance(root, n2) - 2* distance(root, LCA)
        return getDepth(root, node1, 0) + getDepth(root, node2, 0) - 2*getDepth(root, lca, 0);
    }

    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if (root == null) {
            return node;
        }
        if (node.val < root.val) {
            root.left = insertNode(root.left, node);
        } else {
            root.right = insertNode(root.right, node);
        }
        return root;
    }

    public int getLowestCommonAncestor(TreeNode root, Integer n1, Integer n2) {
        // 1. use a map to store child-parent relationship
        Map<Integer, Integer> parent = new HashMap<>();
        // It doesn't matter if we're using a stack or a queue. In this case, all we need is find p or q during the search
        Stack<TreeNode> stack = new Stack<>();
        parent.put(root.val, null);
        stack.push(root);

        // add all child-parent pairs until both p & q are in the map as child
        // after the while loop, both p and q and their parents should be in the map.
        while (!parent.containsKey(n1) || !parent.containsKey(n2)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left.val, node.val);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right.val, node.val);
                stack.push(node.right);
            }
        }

        // 2. create ancestors set, add p, and all p's ancestors to the set
        Set<Integer> ancestors = new HashSet<>();
        while (n1 != null) {
            ancestors.add(n1);
            n1 = parent.get(n1);
        }
        //3. check if q is in p's ancestors list, if not, move p 1 level up
        while (!ancestors.contains(n2))
            n2 = parent.get(n2);
        return n2;
    }

    // get the depth of node. if it is not in the tree, return -1.
    public int getDepth(TreeNode root, int node, int depth) {
        if (root == null) return -1;

        if (node == root.val) {
            return depth;
        }

        // find node in the left subtree. if it does not exist, find in the right subtree;
        int d = getDepth(root.left, node, depth+1);
        if (d >= 0) {
            return d;
        }
        return getDepth(root.right, node, depth+1);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ConstructBSTAndFindDistance solution = new ConstructBSTAndFindDistance();
        System.out.println(solution.bstDistance(new int[]{5,6,3,1,2,4}, 6, 2,4));
    }
}
