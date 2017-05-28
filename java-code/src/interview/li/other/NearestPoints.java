package interview.li.other;

import java.util.*;

public class NearestPoints {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return "(x: " + this.x + ", y: " + this.y + ")";
		}
	}
	// heap to find k points
	public static List<Point> findPoints(int K, final Point center, Point[] points) {
		Comparator<Point> cmp = new Comparator<Point>() {
			public int compare(Point A, Point B) {
				double dd = getDistance(A, center) - getDistance(B, center);
				if (dd > 0) {
					return -1;
				} else if (dd == 0) {
					return 0;
				} else {
					return 1;
				}
			}
		};
		
		PriorityQueue<Point> heap = new PriorityQueue<Point>(K, cmp);
		for (Point p : points) {
			heap.offer(p);
			if (heap.size() > K) {
				heap.poll();
			}
		}
		return new ArrayList<Point>(heap);
	}
	// Quick select to find K points(find Kth smallest element)
	public static List<Point> quickFindPoints(int K, Point center, Point[] points) {
		quickSelect(K, points, center, 0, points.length - 1);
		List<Point> rs = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			rs.add(points[i]);
		}
		return rs;
	}
	
	public static void quickSelect(int K, Point[] points, Point center, int start, int end) {
		Random rd = new Random();
		int pivot = rd.nextInt(end - start + 1) + start; 
		int i = start;
		int j = end - 1;
		double distance = getDistance(points[pivot], center);
		swap(points, pivot, end);
		while (i < j) {
			while (i < j && getDistance(points[i], center) <= distance) {
				i++;
			}
			while (i < j && getDistance(points[j], center) > distance) {
				j--;
			}
			swap(points, i, j);
		}
		swap(points, end, i);
		int amount = i - start + 1;
		if (K > amount) {
			quickSelect(K - amount, points, center, i + 1, end);
		} else if (K < amount) {
			quickSelect(K, points, center, start, i - 1);
		} else {
			return;
		}
	}
	
	public static void swap(Point[] points, int i, int j) {
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}
	
	public static double getDistance(Point A, Point B) {
		int xDistance = Math.abs(A.x - B.x);
		int yDistance = Math.abs(A.y - B.y);
		return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
	}
	
	public static int maxPoints(Point[] points) {
        if (points.length == 0) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();
        int max = 1;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int dup = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    dup++;
                    continue;
                }
                int xDiff = points[i].x - points[j].x;
                int yDiff = points[i].y - points[j].y;
                String key;
                if (xDiff == 0 || yDiff == 0) {
                    key = getKey(xDiff, yDiff);
                } else {
                    int gcd = getGCD(xDiff, yDiff);
                    xDiff /= gcd;
                    yDiff /= gcd;
                    key = getKey(xDiff, yDiff);
                }
                System.out.println("key: " + key);
                if (map.containsKey(key)) {
                    map.put(key, map.get(key + 1));
                } else {
                    map.put(key, 2);
                }
            }
            
            for (int amount : map.values()) {
                max = Math.max(max, amount + dup);
            }
        }
        return max;
    }
    
    public static int getGCD(int x, int y) {
        if (y == 0) {
            return x;
        }
        return getGCD(y, x % y);
    }
    
    public static String getKey(int x, int y) {
        if (x == 0) {
            return "INF";
        }
        if (y == 0) {
            return "0";
        }
        return x + "+" + y;
    }
	
	public static void main(String[] args) {
		Point[] pp = new Point[10];
		for (int i = 10; i >= 1; i--) {
			pp[i - 1] = new Point(i, i);
		}
		//Point center = new Point(0, 0);
		//System.out.println(findPoints(5, center, pp));
		//System.out.println(quickFindPoints(5, center, pp));
		Point[] points = {new Point(0, 0), new Point(-1, -1), new Point(2, 2)};
		System.out.println(maxPoints(points));
	}
}
