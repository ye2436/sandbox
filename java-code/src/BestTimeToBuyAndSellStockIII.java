/**
 * #123. Best Time to Buy and Sell Stock III
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        // f[k, i] represents the max profit up until prices[i] (Note: NOT ending with prices[i]) using at most k transactions.
        // f[k, i] = max(f[k, i-1], prices[i] - prices[j] + f[k-1, j]) { jj in range of [0, ii-1] }
        //          = max(f[k, i-1], prices[i] + max(f[k-1, j] - prices[j]))
        // f[0, i] = 0; 0 times transaction makes 0 profit
        // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
        int[][] dp = new int[3][prices.length];

        // no need to initiate when k=0 or i=0, dp array is already all 0's. so start with k=1 and i=1.
        for (int k=1; k<dp.length; k++) {
            int tmp = dp[k-1][0] - prices[0];
            for (int i=1; i<dp[0].length; i++) {
                dp[k][i] = Math.max(dp[k][i-1], prices[i] + tmp);
                tmp = Math.max(tmp, dp[k-1][i] - prices[i]);
            }
        }

        return dp[2][prices.length-1];
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
