package interview.am.lc2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 505. The Maze II

 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 */
public class MazeII {

    // Dijkstra's algorithm
    // Similar to Maze I, we use iterative BFS, but with a priority queue. The priority queue compare by distance.
    // Since we need to keep track of distance, we will add a parameter to Point.
    // * instead of using a visited matrix, we keep a distance matrix.
    public int shortestDistance_practice(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[0].length == 0) return -1;
        int m = maze.length;
        int n = maze[0].length;
        Integer[][] distance = new Integer[m][n];
        int shortestDist = Integer.MAX_VALUE;
        PriorityQueue<Point> minHeap = new PriorityQueue<>(m * n, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.d - o2.d;
            }
        });
        minHeap.offer(new Point(start[0], start[1], 0));
        int[] dirs = new int[] {0, 1, 0, -1, 0};
        while (!minHeap.isEmpty()) {
            Point curr = minHeap.poll();
            // smaller distance already exists
            if (distance[curr.x][curr.y] != null && distance[curr.x][curr.y] <= curr.d) continue;

            int xx = curr.x;
            int yy = curr.y;
            int dd = curr.d;
            for (int i=0; i<dirs.length-1; i++) {
                while (isValid(maze, xx + dirs[i], yy + dirs[i+1])) {
                    xx += dirs[i];
                    yy += dirs[i+1];
                    dd ++;
                }
                minHeap.offer(new Point(xx, yy, dd));
            }
        }

        return distance[destination[0]][destination[1]] == null ? -1 : distance[destination[0]][destination[1]];
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return -1;
        if (Arrays.equals(start, destination)) return 0;
        int m = maze.length;
        int n = maze[0].length;
        Integer[][] distance = new Integer[m][n];
        int[] dirs = new int[] {0, 1, 0, -1, 0};
        PriorityQueue<Point> minHeap = new PriorityQueue<>(m * n, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.d - o2.d;
            }
        });
        minHeap.offer(new Point(start[0], start[1], 0));
        while (!minHeap.isEmpty()) {
            Point curr = minHeap.poll();
            // if there is already a distance calculated and is smaller
            if (distance[curr.x][curr.y] != null && distance[curr.x][curr.y] <= curr.d) {
                continue;
            }
            distance[curr.x][curr.y] = curr.d;
            for (int i=0; i<dirs.length-1; i++) {
                int xx = curr.x;
                int yy = curr.y;
                int dd = curr.d;
                while (isValid(maze, xx+dirs[i], yy+dirs[i+1])) {
                    xx += dirs[i];
                    yy += dirs[i+1];
                    dd ++;
                }
                minHeap.offer(new Point(xx, yy, dd));
            }
        }
        return distance[destination[0]][destination[1]] == null ? -1 : distance[destination[0]][destination[1]];
    }


    class Point {
        int x;
        int y;
        int d;
        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    private boolean isValid(int[][] maze, int row, int col) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] == 1) return false;
        return true;
    }

}
