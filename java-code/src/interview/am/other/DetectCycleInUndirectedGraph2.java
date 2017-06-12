package interview.am.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Union Find solution
 * http://www.geeksforgeeks.org/union-find/
 */
public class DetectCycleInUndirectedGraph2 {

    // For undirected graph, we have 2 ways to detect a cycle. Union Find and DFS
    // Time complexity of the union-find algorithm is O(ELogV).
    // Time complexity of DFS is                      O(V+E).

    // A union-find algorithm is an algorithm that performs two useful operations on such a data structure:
    // Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
    // Union: Join two subsets into a single subset.
    // Union-Find Algorithm can be used to check whether an undirected graph contains cycle or not.
    // This method assumes that graph doesn't contain any self-loops.
    // * We can keeps track of the subsets in a 1D array, lets call it parent[].
    // * If for any edge, its src and dest are in the same subset, there is a cycle.
    public static boolean detectCycleInUndirectedGraph(Graph graph) { // return true if a cycle exists
        // create parent array and initiate to -1
        int[] parent = new int[graph.V];
        for (int i=0; i<graph.V; i++) {
            parent[i] = -1;
        }

        for (Graph.Edge edge : graph.edges) {
            int x = find(parent, edge.src);
            int y = find(parent, edge.dest);

            if (x == y) return true;

            union(parent, x, y);
        }

        return false;
    }

    // recursively find v's highest ancestor (find which subset it's in)
    // subset's representative (highest ancestor) will have -1 in the array, others will have the index pointing
    // to its direct parent
    private static int find(int[] parent, int v) {
        if (parent[v] == -1) {
            return v;
        }
        return find(parent, parent[v]);
    }

    // union 2 subsets
    private static void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    static class Graph {
        int V; // # of vertices
        int E; // # of edges
        Edge[] edges;

        class Edge {
            int src;
            int dest;
        }

        public Graph(int V, int E) {
            this.V = V;
            this.E = E;
            edges = new Edge[E];
            for (int i=0; i<E; i++) {
                edges[i] = new Edge();
            }
        }
    }

    public static void main(String[] args) {
        /* Let us create following graph
         0
        |  \
        |    \
        1-----2 */
        int V = 3, E = 3;
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        // add edge 1-2
        graph.edges[1].src = 1;
        graph.edges[1].dest = 2;

        // add edge 0-2
        graph.edges[2].src = 0;
        graph.edges[2].dest = 2;

        System.out.println(detectCycleInUndirectedGraph(graph));
    }


}
