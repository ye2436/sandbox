package interview.am;

import java.util.*;

/**
 *  545. Boundary of Binary Tree

 Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

 Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

 The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

 The right-most node is also defined by the same way with left and right exchanged.

 Example 1

 Input:
        1
         \
          2
         / \
        3   4

 Ouput:
 [1, 3, 4, 2]

 Explanation:
 The root doesn't have left subtree, so the root itself is left boundary.
 The leaves are node 3 and 4.
 The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 So order them in anti-clockwise without duplicates and we have [1,3,4,2].

 Example 2

 Input:
        ____1_____
       /          \
      2            3
     / \          /
    4   5        6
       / \      / \
      7   8    9  10

 Ouput:
 [1,2,4,7,8,9,10,6,3]

 Explanation:
 The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 The leaves are node 4,7,8,9,10.
 The right boundary are node 1,3,6,10. (10 is the right-most node).
 So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

 */
public class BoundryOfBinaryTree {

    // Solution 2: Single Pass
    // Use a flag to designate the type of node during the preorder traversal. Its values are:
    // 0 - root, 1 - left boundary node, 2 - right boundary node, 3 - leaf node.
    public List<Integer> boundaryOfBinaryTree2(TreeNode root) {
        List<Integer> left = new LinkedList<>(), right = new LinkedList<>();
        preorder(root, left, right, 0);
        left.addAll(right);
        return left;
    }

    // left list contains both left boundary and leaf nodes.
    public void preorder(TreeNode cur, List<Integer> left, List<Integer> right, int flag) {
        if (cur == null) return;
        if (flag == 2) right.add(0, cur.val);
        else if (flag <= 1 || cur.left == null && cur.right == null) left.add(cur.val);
        preorder(cur.left, left, right, flag <= 1 ? 1 : (flag == 2 && cur.right == null) ? 2 : 3);
        preorder(cur.right, left, right, flag % 2 == 0 ? 2 : (flag == 1 && cur.left == null) ? 1 : 3);
    }


    // Solution 1: Two pass for either subtree
    // get left boundary, left leaf nodes, right leaf nodes, right boundary
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        results.add(root.val);
        results.addAll(getLeftBoundary(root.left));
        results.addAll(getLeafNodes(root.left));
        results.addAll(getLeafNodes(root.right));
        results.addAll(getRightBoundary(root.right));
        return results;
    }

    // start from root.left, does not include root, does not include left-most node (leaf)
    private List<Integer> getLeftBoundary(TreeNode node) {
        if (node == null) return new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        while (node != null) {
            vals.add(node.val);
            node = node.left != null ? node.left : node.right;
        }
        vals.remove(vals.size()-1);
        return vals;
    }

    // start from root.right, does not include root, does not include right-most node (leaf)
    private List<Integer> getRightBoundary(TreeNode node) {
        if (node == null) return new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        while (node != null) {
            vals.add(node.val);
            node = node.right != null ? node.right : node.left;
        }
        vals.remove(vals.size()-1);
        Collections.reverse(vals);
        return vals;
    }

    // start from root.left or root.right, does not include root
    private List<Integer> getLeafNodes(TreeNode node) {
        if (node == null) return new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        // pre-order traversal
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.left == null && curr.right == null) { // leaf node
                vals.add(curr.val);
                continue;
            }
            // push right first, so we get left first when pop
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return vals;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
