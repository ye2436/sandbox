package interview.li.other;

public class TournamentTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int v) {
                val = v;
        }
    }
    
    public static int secMin(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
                return -1;
        }
        int left = -1, right = -1;
        if (root.left.val == root.val) {
                left = secMin(root.left);
        } else {
                left = root.left.val;
        }
        if (root.right.val == root.val) {
                right = secMin(root.right);
        } else {
                right = root.right.val;
        }
        if (left == -1 || right == -1) {
                return left == -1 ? right : left;
        }
        return Math.min(left, right);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(4);
        System.out.println(secMin(root));
    }
}
