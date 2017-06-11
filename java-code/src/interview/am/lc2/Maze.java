package interview.am.lc2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * #490. The Maze
 There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

 Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

 The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 Example 1

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (4, 4)

 Output: true
 Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

 Example 2

 Input 1: a maze represented by a 2D array

 0 0 1 0 0
 0 0 0 0 0
 0 0 0 1 0
 1 1 0 1 1
 0 0 0 0 0

 Input 2: start coordinate (rowStart, colStart) = (0, 4)
 Input 3: destination coordinate (rowDest, colDest) = (3, 2)

 Output: false
 Explanation: There is no way for the ball to stop at the destination.

 Note:
 There is only one ball and one destination in the maze.
 Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
 The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class Maze {

    // BFS - See this problem as a graph
    // Time complexity : O(mn). Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and columns of the maze.
    // Space complexity : O(mn). visited array of size m*n is used and queue size can grow upto m*n in worst case.
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) return false;
        if (Arrays.equals(start, destination)) return true;
        int m = maze.length;
        int n = maze[0].length;
        int[] dirs = new int[] {0, 1, 0, -1, 0};
        boolean[][] visited = new boolean[m][n];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1]));
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            // determine new pointers from 4 directions
            for (int i=0; i<dirs.length-1; i++){
                int xx = curr.x;
                int yy = curr.y;
                while (isValid(maze, xx+dirs[i], yy+dirs[i+1])) {
                    xx += dirs[i];
                    yy += dirs[i+1];
                }
                if (xx == destination[0] && yy == destination[1]) return true;
                if (visited[xx][yy]) continue;
                visited[xx][yy] = true;
                queue.offer(new Point(xx, yy));
            }
        }
        return false;
    }

    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // DFS - but different from usual path finding where we look at four directions that are 1 cell away from the original point,
    // Here if we move at 1 direction, it won't stop until it hit a wall or out of boundary
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] startedHere = new boolean[maze.length][maze[0].length]; // instead of using visited, use started here. so we can quickly eliminate
        return dfs(maze, start, destination, startedHere);
    }

    // currently at point(i,j) going at direction given, return if we can reach the destination
    // di - direction i: -1 up, +1 down
    // dj - direction j: -1 left, +1 down
    private boolean dfs (int[][] maze, int[] start, int[] dest, boolean[][] startedHere) {
        if (Arrays.equals(start, dest)) return true;
        if (startedHere[start[0]][start[1]]) return false;

        startedHere[start[0]][start[1]] = true;

        int[] newStart = roll(maze, start[0], start[1], -1, 0);
        if (dfs(maze, newStart, dest, startedHere)) {
            return true;
        }
        newStart = roll(maze, start[0], start[1], 1, 0);
        if (dfs(maze, newStart, dest, startedHere)) {
            return true;
        }
        newStart = roll(maze, start[0], start[1], 0, -1);
        if (dfs(maze, newStart, dest, startedHere)) {
            return true;
        }
        newStart = roll(maze, start[0], start[1], 0, 1);
        if (dfs(maze, newStart, dest, startedHere)) {
            return true;
        }
        return false;
    }

    // find the new coordinates if the ball is rolled in that direction
    private int[] roll(int[][] maze, int row, int col, int rowInc, int colInc) {
        while (isValid(maze, row + rowInc, col + colInc)) {
            row += rowInc;
            col += colInc;
        }
        return new int[] {row, col};
    }

    private boolean isValid(int[][] maze, int row, int col) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] == 1) return false;
        return true;
    }
}
