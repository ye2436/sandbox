package interview.am.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/find-whether-path-two-cells-matrix/
 * Find whether there is path between two cells in matrix
 Given N X N matrix filled with 1 , 0 , 2 , 3 . Find whether there is a path possible from source to destination, traversing through blank cells only. You can traverse up, down, right and left.

 A value of cell 1 means Source.
 A value of cell 2 means Destination.
 A value of cell 3 means Blank cell.
 A value of cell 0 means Blank Wall.
 Note : there is only single source and single destination(sink).

 Examples:

 Input : M[3][3] = {{ 0 , 3 , 2 },
                    { 3 , 3 , 0 },
                    { 1 , 3 , 0 }};
 Output : Yes

 Input : M[4][4] = {{ 0 , 3 , 1 , 0 },
                    { 3 , 0 , 3 , 3 },
                    { 2 , 3 , 0 , 3 },
                    { 0 , 3 , 3 , 3 }};
 Output : Yes
 */
public class FindPath {
    // The idea is to use Breadth First Search. Consider each cell as a node and each boundary between
    // any two adjacent cells be an edge. so total number of Node is N*N.
    // * To do that, we need to create a graph with each individual node that are not 0. Each cell is a node/vertex, they are connected to the ones
    // that they are adjacent to.
    // * During the process, we will find 1 & 2, store them to variables. After the graph is completed, do a BFS

    public boolean pathExists(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int V = m * n; // number of vertice (+2?)
        Graph graph = new Graph(V);

        Integer start = null;
        Integer end = null;

        int k = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] != 0) {
                    // if node (i,j) is valid to visit, check if all 4 adjacent cells are valid to visit
                    // add edge between the two vertices if they are both valid
                    if (isValid(i, j+1, matrix)) {
                        graph.addEdge(k, k+1); // right
                    }
                    if (isValid(i, j-1, matrix)) {
                        graph.addEdge(k, k-1); // left
                    }
                    if (isValid(i+1, j, matrix)) {
                        graph.addEdge(k, k+n); // down
                    }
                    if (isValid(i-1, j, matrix)) {
                        graph.addEdge(k, k-n); // up
                    }
                }

                if (matrix[i][j] == 1) {
                    start = k;
                }
                if (matrix[i][j] == 2) {
                    end = k;
                }

                k++;
            }
        }

        if (start == null || end == null) return false;
        return graph.bfs(start, end);
    }

    private boolean isValid(int i, int j, int[][] matrix) {
        if (i<0 || i>=matrix.length || j<0 || j>=matrix[0].length || matrix[i][j] == 0) return false;
        return true;
    }


    ///////////////////
    // Graph Implementation http://opendatastructures.org/ods-java/12_2_AdjacencyLists_Graph_a.html
    public class Graph {
        int n; // number of vertices
        List<Integer>[] adj;

        public Graph(int n) {
            this.n = n;
            adj = (List<Integer>[])new List[n];
            for (int i = 0; i < n; i++)
                adj[i] = new ArrayList<>();
        }

        // Adds the undirected edge v-w to this graph.
        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }

        // BFS to find if path exists between 2 vertices
        public boolean bfs (int start, int end) {
            if (start == end) return true;
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start); // if multiple sources, enqueue all sources here
            visited[start] = true;

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (int neighbor : adj[curr]) { // for each vertex in current's adjacent list
                    if (neighbor == end) return true; // found the end, path exists

                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }

            return false;
        }
    }
}
