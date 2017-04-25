package lc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * #149. Max Points on a Line
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class MaxPointOnALine {

    public static int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;

        int max = 1; // there is at least 1 point on a line
        for (int i=0; i<points.length-1; i++) {
            Map<BigDecimal, Integer> count = new HashMap<>(); // count based on slope
            int countOfSamePoint = 0; // count of points that are same as points[i] (excludes points[i])
            BigDecimal slope;
            int maxStartHere = 1; // max starting from point[i]
            for (int j=i+1; j<points.length; j++) {
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    countOfSamePoint++;
                    continue;
                }
                if (points[j].x == points[i].x) {
                    slope = BigDecimal.valueOf(Integer.MAX_VALUE);
                } else if (points[j].y == points[i].y) {
                    slope = BigDecimal.ZERO;
                } else {
                    slope = BigDecimal.valueOf(points[j].y - points[i].y).divide(BigDecimal.valueOf(points[j].x - points[i].x), 16, RoundingMode.HALF_UP);
                }
                if (!count.containsKey(slope)) {
                    count.put(slope, 1);
                }
                count.put(slope, count.get(slope)+1);
                maxStartHere = Math.max(maxStartHere, count.get(slope));
            }
            max = Math.max(max, maxStartHere+countOfSamePoint);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(94911150d/94911151d);
        System.out.println(94911151d/94911152d);
        System.out.println(BigDecimal.valueOf(94911150d).divide(BigDecimal.valueOf(94911151d), 16, RoundingMode.HALF_UP).toString());
        System.out.println(BigDecimal.valueOf(94911151d).divide(BigDecimal.valueOf(94911152d), 16, RoundingMode.HALF_UP).toString());
        Point[] pArray = getPointArray();
        System.out.println(maxPoints(pArray));
    }

    private static Point[] getPointArray() {
        // y = 3x-1
        /*Point p1 = new Point(1,2);
        Point p2 = new Point(2,5);
        Point p3 = new Point(3,8);
        Point p4 = new Point(4,4);
        return new Point[]{p1,p2,p3,p4};*/

        // [[0,0],[94911151,94911150],[94911152,94911151]]
        Point p1 = new Point(0,0);
        Point p2 = new Point(94911151,94911150);
        Point p3 = new Point(94911152,94911151);
        return new Point[]{p1,p2,p3};
    }

    private static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
}
