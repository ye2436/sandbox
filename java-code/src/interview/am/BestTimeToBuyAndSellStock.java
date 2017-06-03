package interview.am;

/**
 * #121. Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * In this case, no transaction is done, i.e. max profit = 0.
 *
 */
public class BestTimeToBuyAndSellStock {

    // loop from right to left, keep track of the max number on the right side of the current number
    // get profit = max - curr
    // update max profit as needed
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        // maxSellPrice: max number on the right side of current i. example 1: [6,6,6,6,4,0]
        // profit = maxSellPrice - prices[i]
        int maxSellPrice = prices[prices.length-1];
        int maxProfit = 0;
        for (int i=prices.length-2; i>=0; i--) {
            maxProfit = Math.max(maxProfit, maxSellPrice - prices[i]);
            maxSellPrice = Math.max(maxSellPrice, prices[i]);
        }

        return maxProfit;
    }

    // loop from left to right, keep track of the min number on the left side of the current number
    // get profit = curr - min
    // update max profit as needed
    public static int maxProfit_2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        // minBuyPrice: min number on the left side of current i. example 1: [0,7,1,1,1,1]
        // profit = prices[i] - minBuyPrice
        int minBuyPrice = prices[0];
        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minBuyPrice);
            minBuyPrice = Math.min(minBuyPrice, prices[i]);
        }

        return maxProfit;
    }

    // Kadane's algorithm
    public static int maxProfit_3(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int local = 0;
        int global = 0;
        for(int i=0;i<prices.length-1;i++) {
            local = Math.max(local+prices[i+1]-prices[i],0);
            global = Math.max(local, global);
        }
        return global;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
