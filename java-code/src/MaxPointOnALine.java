import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.HashMap;
import java.util.Map;

/**
 * #149. Max Points on a Line
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class MaxPointOnALine {

    public static int maxPoints(Point[] points) {
        if (points == null || points.length == 0) return 0;

        // if y = ax + b
        // a = y/x;
        // b = y-ax;
        Map<Integer, Map<Integer, Integer>> count = new HashMap<>(); // a -> b -> count
        int max = 0;
        for (Point point : points) {
            int a = point.y / point.x;
            int b = point.y - a * point.x;
            if (!count.containsKey(a)) {
                count.put(a, new HashMap<>());
            }
            if (!count.get(a).containsKey(b)) {
                count.get(a).put(b, 0);
            }
            count.get(a).put(b, count.get(a).get(b)+1);
            max = Math.max(max, count.get(a).get(b));
        }

        return max;
    }

    public static void main(String[] args) {
        Point[] pArray = getPointArray();
        System.out.println(maxPoints(pArray));
    }

    private static Point[] getPointArray() {
        // y = 3x-1
        Point p1 = new Point(1,2);
        Point p2 = new Point(2,5);
        Point p3 = new Point(3,8);

        Point p4 = new Point(4,4);

        return new Point[]{p1,p2,p3,p4};
    }

    private static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
}
