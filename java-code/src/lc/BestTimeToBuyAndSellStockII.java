package lc;

/**
 * #122. Best Time to Buy and Sell Stock II
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0) return 0;

        // 既然交易次数没有限定，可以看出我们只要对于每次两天差价大于0的都进行交易，就可以得到最大利润。
        int res = 0;
        for (int i=0; i<prices.length-1; i++) {
            int diff = prices[i+1] - prices[i];
            if (diff > 0) {
                res += diff;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
