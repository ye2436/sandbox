package interview.li.other;

public class MirrorTree {
	public static void getMirror(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		getMirror(root.left);
		getMirror(root.right);
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.right = new TreeNode(4);
		getMirror(root);
		System.out.print(root.right.right.val);
		/*
		TreeNode cur = root;
		
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(cur);
		while (q.size() > 0) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode temp = q.poll();
				System.out.print(temp.val);
				if (temp.left != null) {
					q.offer(temp.left);
				}
				if (temp.right != null) {
					q.offer(temp.right);
				}
			}
			System.out.println();
		}
		*/
	}
}
