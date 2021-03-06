package interview.fb.lc;

/**
 * #117. Populating Next Right Pointers in Each Node II
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given lc.tree could be any binary lc.tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary lc.tree,
 *           1
 *         /  \
 *        2    3
 *       / \    \
 *      4   5    7
 * After calling your function, the lc.tree should look like:
 *           1 -> NULL
 *         /  \
 *        2 -> 3 -> NULL
 *       / \    \
 *      4-> 5 -> 7 -> NULL
 */
public class PopulatingNextRightPointersInEachNodeII {

    public void connect(TreeLinkNode root) {
        TreeLinkNode lastHead = root;
        TreeLinkNode currHead = null;
        while (lastHead != null) {
            TreeLinkNode lastCurr = lastHead; // previous/parent level
            TreeLinkNode pre = null; // previous node on the child level
            while (lastCurr != null) {

                if (lastCurr.left != null) {
                    if (currHead == null) {
                        currHead = lastCurr.left;
                        pre = currHead;
                    } else {
                        pre.next = lastCurr.left;
                        pre = pre.next;
                    }
                }
                if (lastCurr.right != null) {
                    if (currHead == null) {
                        currHead = lastCurr.right;
                        pre = currHead;
                    } else {
                        pre.next = lastCurr.right;
                        pre = pre.next;
                    }
                }

                lastCurr = lastCurr.next; // moves to the right
            }
            // can't use level_start = level_start.left;
            // left does not necessarily exist
            lastHead = currHead;
            currHead = null;
        }
    }

    public void connect2(TreeLinkNode root) {
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
            root = root.next;
            if (root == null) {
                pre = dummyHead;
                root = dummyHead.next;
                dummyHead.next = null;
            }
        }
    }

    public static void main(String[] args) {
        PopulatingNextRightPointersInEachNodeII instance = new PopulatingNextRightPointersInEachNodeII();
        TreeLinkNode root = instance.generate();
        instance.connect(root);
        while (root != null) {
            TreeLinkNode curr = root;
            while (curr != null) {
                System.out.println(curr.val + "->");;
                curr = curr.next;
            }

            root = root.left;
        }
    }

    private TreeLinkNode generate() {

        TreeLinkNode t1 = new TreeLinkNode(1);
        TreeLinkNode t2 = new TreeLinkNode(2);
        TreeLinkNode t3 = new TreeLinkNode(3);
        TreeLinkNode t4 = new TreeLinkNode(4);
        TreeLinkNode t5 = new TreeLinkNode(5);
        //TreeLinkNode t6 = new TreeLinkNode(6);
        TreeLinkNode t7 = new TreeLinkNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        //t3.left = t6;
        t3.right = t7;
        return t1;
    }

    private class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}
