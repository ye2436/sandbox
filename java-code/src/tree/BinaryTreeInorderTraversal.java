package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * #94. Binary Tree Inorder Traversal
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree [1,null,2,3],
 *  1
 *   \
 *   2
 *   /
 *  3
 * return [1,3,2].
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

    // recursive - 时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)
    /**
     * inorder(node)
     *  if (node = null)
     *      return
     *  inorder(node.left)
     *  visit(node)
     *  inorder(node.right)
     *
     * //https://en.wikipedia.org/wiki/Tree_traversal
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            result.addAll(inorderTraversal(root.left));
            result.add(root.val);
            result.addAll(inorderTraversal(root.right));
        }
        return result;
    }

    // iterative - push all the node to stack if not null until reach the bottom.
    //             else pop out a node visit it and then make the right the next node
    // 算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
    /**
     * iterativeInorder(node)
     *  s ← empty stack
     *  while (not s.isEmpty() or node ≠ null)
     *      if (node ≠ null)
     *          s.push(node)
     *          node ← node.left
     *      else
     *          node ← s.pop()
     *          visit(node)
     *          node ← node.right
     *
     * //https://en.wikipedia.org/wiki/Tree_traversal
     */
    public static List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    // 如果用常量空间来中序遍历一颗二叉树，这种方法叫 Morris Traversal。
    // 想用O(1)空间进行遍历，因为不能用栈作为辅助空间来保存付节点的信息，重点在于当访问到子节点的时候如何重新回到父节点（当然这里是指没有父节点指针，如果有其实就比较好办，一直找遍历的后驱结点即可）。
    // Morris遍历方法用了线索二叉树，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
    // 线索二叉树的定义如下:
    //  “一个二叉树通过如下的方法“穿起来”：所有应该为空的右孩子指针指向该节点在中序序列中的后继，所有应该为空的左孩子指针指向该节点的中序序列的前驱。”
    // 算法具体分情况如下：
    //  1. 如果当前结点的左孩子为空，则输出当前结点并将其当前节点赋值为右孩子。
    //  2. 如果当前节点的左孩子不为空，则寻找当前节点在中序遍历下的前驱节点（也就是当前结点左子树的最右孩子）。接下来分两种情况：
    //      a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（做线索使得稍后可以重新返回父结点）。然后将当前节点更新为当前节点的左孩子。
    //      b) 如果前驱节点的右孩子为当前节点，表明左子树已经访问完，可以访问当前节点。将它的右孩子重新设为空（恢复树的结构）。输出当前节点。当前节点更新为当前节点的右孩子。
    // Morris traversal is an implementation of in-order traversal that uses threading:
    //  Create links to the in-order successor.
    //  Print the data using these links.
    //  Revert the changes to restore original tree.
    public static List<Integer> inorderTraversal_3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre = null;
        while (curr != null) {
            if (curr.left == null) {
                result.add(curr.val);
                curr = curr.right;
            } else {
                // finding predecessor(前驱) of current node -- that is the right most child of current node's left subtree
                pre = curr.left; // 初始为左子树的根部
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else if (pre.right == curr) {
                    result.add(curr.val);
                    pre.right = null;
                    curr = curr.right;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(inorderTraversal(generate()));
        System.out.println(inorderTraversal_2(generate()));
        System.out.println(inorderTraversal_3(generate()));
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

        TreeNode t1 = getTreeNode(1);
        TreeNode t2 = getTreeNode(2);
        TreeNode t3 = getTreeNode(3);
        TreeNode t4 = getTreeNode(4);
        TreeNode t5 = getTreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        return t1;
    }

    private static TreeNode getTreeNode(int val) {
        return new TreeNode(val);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
