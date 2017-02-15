package tree;

/**
 * #116. Populating Next Right Pointers in Each Node
 * Given a binary tree
 *  struct TreeLinkNode {
 *      TreeLinkNode *left;
 *      TreeLinkNode *right;
 *      TreeLinkNode *next;
 *  }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *           1
 *         /  \
 *        2    3
 *       / \  / \
 *      4  5  6  7
 * After calling your function, the tree should look like:
 *           1 -> NULL
 *         /  \
 *        2 -> 3 -> NULL
 *       / \  / \
 *      4->5->6->7 -> NULL
 */
public class PopulatingNextRightPointersInEachNode {

    public static void connect(TreeLinkNode root) {
        TreeLinkNode level_start = root;
        while (level_start != null) {
            TreeLinkNode curr = level_start; // previous/parent level
            while (curr != null) {
                if (curr.left != null) {
                    curr.left.next = curr.right;
                }
                if (curr.right != null && curr.next != null) {
                    curr.right.next = curr.next.left;
                }

                curr = curr.next; // moves to the right
            }
            level_start = level_start.left;
        }

    }

    public static void connect_my(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode node = root;
        while (node.left != null) {
            TreeLinkNode parent = node; // previous/parent level
            TreeLinkNode prev = null; // right node of the previous tree on the same level
            while (parent != null) {
                if (prev != null) {
                    prev.next = parent.left;
                }
                parent.left.next = parent.right;
                prev = parent.right;

                parent = parent.next; // moves to the right
            }
            node = node.left;
        }

    }

    public static void main(String[] args) {
        TreeLinkNode root = generate();
        connect(root);
        while (root != null) {
            TreeLinkNode curr = root;
            while (curr != null) {
                System.out.println(curr.val + "->");;
                curr = curr.next;
            }

            root = root.left;
        }
    }

    private static TreeLinkNode generate() {

        TreeLinkNode t1 = new TreeLinkNode(1);
        TreeLinkNode t2 = new TreeLinkNode(2);
        TreeLinkNode t3 = new TreeLinkNode(3);
        TreeLinkNode t4 = new TreeLinkNode(4);
        TreeLinkNode t5 = new TreeLinkNode(5);
        TreeLinkNode t6 = new TreeLinkNode(6);
        TreeLinkNode t7 = new TreeLinkNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        return t1;
    }

    private static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}
