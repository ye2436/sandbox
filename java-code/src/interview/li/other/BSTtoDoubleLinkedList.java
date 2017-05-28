package interview.li.other;

public class BSTtoDoubleLinkedList {
	
	public static void convert(TreeNode root, TreeNode[] pre) {
		if (root == null) {
			return;
		}
		
		convert(root.left, pre);
		root.left = pre[0];
		if (pre[0] != null) {
			pre[0].right = root;
		}
		pre[0] = root;
		convert(root.right, pre);
	}
	
	public static TreeNode cc(TreeNode root) {
		TreeNode[] pre = new TreeNode[1];
		convert(root, pre);
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(2);
		root.right = new TreeNode(5);
		TreeNode rs = cc(root);
		while (rs != null) {
			System.out.print(rs.val + " ");
			rs = rs.right;
		}
	}
	
	
}
