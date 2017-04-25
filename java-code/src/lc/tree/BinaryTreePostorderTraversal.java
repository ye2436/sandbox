package lc.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * #145. Binary Tree Postorder Traversal
 * Given a binary lc.tree, return the postorder traversal of its nodes' values.
 * For example:
 * Given binary lc.tree {1,#,2,3},
 *      1
 *       \
 *       2
 *      /
 *     3
 * return [3,2,1].
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {

    // recursive - 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。
    /**
     * postorder(node)
     *  if (node = null)
     *      return
     *  postorder(node.left)
     *  postorder(node.right)
     *  visit(node)
     *
     * Source: https://en.wikipedia.org/wiki/Tree_traversal#Post-order_2
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.addAll(postorderTraversal(root.left));
            res.addAll(postorderTraversal(root.right));
            res.add(root.val);
        }
        return res;
    }

    // iterative - 算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。
    // use a variable lastNodeVisited to check if the right node is visited
    /**
     * iterativePostorder(node)
     *  s ← empty stack
     *  lastNodeVisited ← null
     *  while (not s.isEmpty() or node ≠ null)
     *      if (node ≠ null)
     *          s.push(node)
     *          node ← node.left
     *      else
     *          peekNode ← s.peek()
     *          // if right child exists and traversing node
     *          // from left child, then move right
     *          if (peekNode.right ≠ null and lastNodeVisited ≠ peekNode.right)
     *              node ← peekNode.right
     *          else
     *              visit(peekNode)
     *              lastNodeVisited ← s.pop()
     *
     * Source: https://en.wikipedia.org/wiki/Tree_traversal#Post-order_2
     */
    public static List<Integer> postorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisited = null;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else { // stack not empty
                TreeNode peek = stack.peek();
                // if right child exists and is not visited, visit right node first
                if (peek.right != null && peek.right != lastVisited) {
                    root = peek.right;
                } else { // else visit the node and reset the lastNodeVisited
                    res.add(peek.val);
                    lastVisited = stack.pop();
                }
            }
        }

        return res;
    }

    // Morris Traversal: 利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了
    public static List<Integer> postorderTraversal_3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;


        return res;
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(postorderTraversal(root));
        System.out.println(postorderTraversal_2(root));
        System.out.println(postorderTraversal_3(root));
    }

    private static TreeNode generate() {
        /*
        *       1
        *      / \
        *     2   3
        *    / \
        *   4   5
        *
        * Pre-order (NLR):  1 2 4 5 3
        * In-order (LNR):   4 2 5 1 3
        * Post-order (LRN): 4 5 2 3 1
        * */

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        return t1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
