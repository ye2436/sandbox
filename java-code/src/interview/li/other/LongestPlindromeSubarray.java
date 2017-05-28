package interview.li.other;

public class LongestPlindromeSubarray {
	public static int LPS(int[] a) {
		int[][] dp = new int[a.length][a.length];
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}
		for (int i = dp.length - 2; i >= 0; i--) {
			for (int j = i + 1; j < dp.length; j++) {
				if (a[i] == a[j]) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][dp.length - 1];
	}
	
	public static void main(String[] args) {
		int[] a = {4, 4, 1, 2, 4, 4};
		System.out.print(LPS(a));
	}
	
	
}
