package interview.am.other;

import java.util.ArrayList;
import java.util.List;

/**
 * DFS solution
 * http://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 */
public class DetectCycleInUndirectedGraph {

    // For undirected graph, we have 2 ways to detect a cycle. Union Find and DFS
    // Time complexity of the union-find algorithm is O(ELogV).
    // Time complexity of DFS is                      O(V+E).
    // We do a DFS traversal of the given graph. For every visited vertex ‘v’, if there is an adjacent ‘u’ such that
    // u is already visited and u is not parent of v, then there is a cycle in graph. If we don’t find such an adjacent
    // for any vertex, we say that there is no cycle. The assumption of this approach is that there are no parallel edges
    // between any two vertices.

    public static boolean detectCycleInUndirectedGraph(Graph graph) { // return true if a cycle exists
        boolean[] visited = new boolean[graph.V];
        for (int i=0; i<graph.V; i++) {
            if (!visited[i]) {
                if (isCyclic(i, graph, visited, -1)) return true;
            }
        }
        return false;
    }

    // DFS
    private static boolean isCyclic(int v, Graph graph, boolean[] visited, int parent) {
        visited[v] = true;
        for (int w : graph.adj[v]) {
            if (visited[w]) {
                if (v != parent) return true;
            } else {
                if (isCyclic(w, graph, visited, v)) return true;
            }
        }
        return false;
    }

    static class Graph {
        int V;
        List<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            adj = new List[V];
            for (int i=0; i<V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(2, 3);
        System.out.println(detectCycleInUndirectedGraph(graph));
    }


}
