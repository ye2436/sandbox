package lc.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * #144. Binary Tree Preorder Traversal
 * Given a binary lc.tree, return the preorder traversal of its nodes' values.
 * For example:
 * Given binary lc.tree {1,#,2,3},
 *      1
 *       \
 *       2
 *      /
 *     3
 * return [1,2,3].
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePreorderTraversal {

    // recursive - 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)。
    /**
     * preorder(node)
     *  if (node = null)
     *      return
     *  visit(node)
     *  preorder(node.left)
     *  preorder(node.right)
     *
     * Source: https://en.wikipedia.org/wiki/Tree_traversal#Pre-order_2
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.add(root.val);
            res.addAll(preorderTraversal(root.left));
            res.addAll(preorderTraversal(root.right));
        }
        return res;
    }

    // iterative - 算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。
    // visit the node first, and then push right and left (right before left so that left is on top of the stack)
    /**
     * iterativePreorder(node)
     *  if (node = null)
     *      return
     *  s ← empty stack
     *  s.push(node)
     *  while (not s.isEmpty())
     *      node ← s.pop()
     *      visit(node)
     *      if (node.right ≠ null)
     *          s.push(node.right)
     *      if (node.left ≠ null)
     *          s.push(node.left)
     *
     * Source: https://en.wikipedia.org/wiki/Tree_traversal#Pre-order_2
     */
    public static List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return res;
    }

    // Morris Traversal: 利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了
    // 先序和中序的方法几乎完全一样，除了访问父节点的时机
    public static List<Integer> preorderTraversal_3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode curr = root;
        TreeNode pre = null;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                // find predecessor node of curr - the right most node of left sub lc.tree, or left sub lc.tree itself if it has no right child
                pre = curr.left;
                while (pre.right != null && pre.right != curr) { // check pre.right != curr 防止再次回到curr时，找pre的时候造成死循环
                    pre = pre.right;
                }

                if (pre.right == null) { // 第一次访问curr，建立pre和curr之间的thread，然后访问左子树
                    res.add(curr.val);
                    pre.right = curr;
                    curr = curr.left;
                } else { // pre.right == curr, 由于有线索指回curr，这一定是第二次访问curr。删除线索，恢复原结构，访问curr的右子树
                    pre.right = null;
                    curr = curr.right;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = generate();
        System.out.println(preorderTraversal(root));
        System.out.println(preorderTraversal_2(root));
        System.out.println(preorderTraversal_3(root));
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
