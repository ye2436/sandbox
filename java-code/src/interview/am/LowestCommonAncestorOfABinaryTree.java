package interview.am;

import java.util.*;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w
 * as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 *          _______3______
 *         /              \
 *     ___5__          ___1__
 *    /      \        /      \
 *   6      _2       0       8
 *         /  \
 *        7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 */
public class LowestCommonAncestorOfABinaryTree {

    public TreeNode lca_recursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        if (root == p) return p;
        if (root == q) return q;

        TreeNode left = lca_recursive(root.left, p, q);
        TreeNode right = lca_recursive(root.right, p, q);

        if (left != null && right != null) return root;
        if (left != null) return left;
        return right;
    }

    // find p & 1 in root & record every (node, parent) pair in hash map in the process
    // pick 1, say p, map out all its ancestors, including itself
    // check if q is in the ancestors, if not, move q 1 level up
    public TreeNode lca_iterative(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        parentMap.put(root, null);
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                parentMap.put(node.left, node);
            }
            if (node.right != null) {
                queue.offer(node.right);
                parentMap.put(node.right, node);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parentMap.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parentMap.get(q);
        }

        return q;
    }


    // recursive
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) {
            return null;
        }

        if(root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    // iterative
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 1. use a map to store child-parent relationship
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        // It doesn't matter if we're using a stack or a queue. In this case, all we need is find p or q during the search
        Stack<TreeNode> stack = new Stack<>();
        parent.put(root, null);
        stack.push(root);

        // add all child-parent pairs until both p & q are in the map as child
        // after the while loop, both p and q and their parents should be in the map.
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // 2. create ancestors set, add p, and all p's ancestors to the set
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        //3. check if q is in p's ancestors list, if not, move p 1 level up
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    // --------------- Another Version (with parent pointer) -------------------------

    class Node {
        int key;
        Node left, right, parent;

        Node(int key)
        {
            this.key = key;
            left = right = parent = null;
        }
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null || q == null) return null;
        if (p == q) return p;

        // find all ancestors of p and store in a set
        Set<Node> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = p.parent;
        }
        // check if q is p's ancestor, if not check p's parent
        while (q != null) {
            if (ancestors.contains(q)) {
                return q;
            }
            q = q.parent;
        }

        return null;
    }
}
