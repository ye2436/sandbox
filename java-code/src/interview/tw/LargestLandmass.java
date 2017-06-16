package interview.tw;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yechen on 4/27/17.
 */
public class LargestLandmass {

    public static int largest2(char[][] map) {
        if (map == null || map.length == 0 || map[0].length ==0) return 0;
        int largestLandmass = 0;
        int m = map.length;
        int n = map[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (!visited[i][j]) {
                    largestLandmass = Math.max(largestLandmass, dfs(map, visited, i, j));
                }
            }
        }

        return largestLandmass;
    }

    private static int dfs(char[][] map, boolean[][] visited, int i, int j) {
        if (i<0 || i>=map.length || j<0 || j>=map[0].length || map[i][j] == '.' || visited[i][j]) return 0;
        visited[i][j] = true;
        int land = 1 + dfs(map, visited, i-1, j) + dfs(map, visited, i+1, j)
                + dfs(map, visited, i, j-1) + dfs(map, visited, i, j+1);
        return land;
    }

    public static int largest(char[][] map) {
        if (map == null || map.length == 0 || map[0].length ==0) return 0;
        int largestLandmass = 0;
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                List<Integer> size = Arrays.asList(0);
                helper(map, i, j, new boolean[map.length][map[0].length], size);
                if (size.get(0) > largestLandmass) {
                    largestLandmass = size.get(0);
                }
            }
        }

        return largestLandmass;
    }

    private static void helper (char[][] map, int i, int j, boolean[][] visited, List<Integer> size) {
        if (i < 0 || j < 0 || i == map.length || j == map[0].length) return;

        if (map[i][j] == '.' || visited[i][j]) {
            return;
        }
        if (map[i][j] == 'L') {
            size.set(0, size.get(0)+1);
        }
        visited[i][j] = true;
        helper(map, i, j+1, visited, size);
        helper(map, i, j-1, visited, size);
        helper(map, i+1, j, visited, size);
        helper(map, i-1, j, visited, size);
    }

    public static void main(String[] args) {
        char[][] map = parse(new String[] {
                "....LL.L",
                "...LLLLL",
                "..L...L.",
                ".LL.LL..",
                "LLLL.L.."
        });
        System.out.println(largest(map));
        System.out.println(largest2(map));
    }

    public static char[][] parse(String[] input) {
        char[][] map = new char[input.length][];
        for(int i = 0; i < input.length; i++) {
            map[i] = input[i].toCharArray();
        }
        return map;
    }
}
