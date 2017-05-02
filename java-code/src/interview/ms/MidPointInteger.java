package interview.ms;

/**
 * Given a set of integer dots (like(1,5)), how can you find a pair that has an integer mid-point?
 * For example, (1,1) and (3,3) have an integer mid-point of (2,2), while (1,1) and (3,2) do not.
 * Tell the complexity of your code.
 * What if the dots are N-dimensianal?
 *
 * find any one pair of points that their midpoint is also integer point.
 * 要用两种方法，第一种是不用extra memory，第二种是用extra memory
 */
public class MidPointInteger {
    // Let's start from one dimensional points. In order for a pair of points to have an integer mid-point,
    // both points should either be odd or even.
    // Given a set of n odd (or even) points, there are nC2 pairs that have an integer mid-point.
    // Time complexity is O(n) time for counting the number of odd/even points.
    // Scaling up to two-dimensional case:
    // do the above operation for the x-axis; divide the given set into set_even and set_odd.
    // For each set, do the same operation as above. Time complexity is O(2n) = O(n).
    // For N-dimensional case: the time complexity is also O(n).

    public Point[] hasIntegerMidPoint(Point[] points) {
        Point[] pair = new Point[2];

        return pair;
    }

    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = this.y;
        }
    }

}
