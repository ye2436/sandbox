package lc;

/**
 * #135. lc.Candy
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
public class Candy {
    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;

        // loop through the ratings twice, once from left to right - to get the min candy based on the ratings on the left side
        // another loop from right to left - to get the min candy based on the ratings on the right side
        // take the larger one from two results - it is the min candy for this child
        // add up
        int len = ratings.length;
        int[] candies = new int[len];
        candies[0] = 1;
        for (int i=1; i<len; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            } else {
                candies[i] = 1;
            }
        }
        int sum = candies[len-1]; // the last one in candies is already the correct number since there is no one on the right side of this child
        candies[len-1] = 1; // reset to 1, because it now represents min candy based on the right ratings
        for (int i=len-2; i>=0; i--) {
            if (ratings[i] > ratings[i+1]) {
                sum += Math.max(candies[i], candies[i+1] + 1);
                candies[i] = candies[i+1] + 1;
            } else {
                sum += candies[i]; // same as Math.max(candies[i], 1), coz candies[i] can't be smaller than 1
                candies[i] = 1;
            }
        }
        return sum;
    }

    public static void main (String[] args) {
        int[] ratings = {};
        System.out.println(candy(ratings));
    }
}
