package interview.am.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an undirected graph, print all connected components line by line.
 * http://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/
 */
public class ConnectedComponents {

    // Finding connected components for an undirected graph is an easier task. We simple need to do either BFS or DFS starting from
    // every unvisited vertex, and we get all strongly connected components. Below are steps based on DFS.
    // 1) Initialize all vertices as not visited.
    // 2) Do following for every vertex 'v'.
    // (a) If 'v' is not visited before, call DFSUtil(v)
    // (b) Print new line character
    //
    // DFSUtil(v)
    // 1) Mark 'v' as visited.
    // 2) Print 'v'
    // 3) Do following for every adjacent 'u' of 'v'.
    //      If 'u' is not visited, then recursively call DFSUtil(u)
    public static List<List<Integer>> connectedComponents(Graph graph) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[graph.V];
        for (int i=0; i<graph.V; i++) {
            if (!visited[i]) {
                List<Integer> currList = new ArrayList<>();
                dfs(graph, i, visited, currList);
                res.add(new ArrayList<>(currList));
            }
        }
        return res;
    }

    public static void dfs(Graph graph, int v, boolean[] visited, List<Integer> currList) {
        visited[v] = true;
        currList.add(v);

        for (int w : graph.adj[v]) {
            if (!visited[w]) {
                dfs(graph, w, visited, currList);
            }
        }
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
        Graph graph = new Graph(5);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(3, 4);
        System.out.println(connectedComponents(graph));
    }
}
