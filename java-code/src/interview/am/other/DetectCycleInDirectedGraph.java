package interview.am.other;

import java.util.*;

/**
 * 有一堆EVENT, 每个EVENT记录WINNER和LOSER, 比如【A,B】表示A 是WINNER， B是LOSER, 判断是否存在A赢B， B赢C， C赢A 的情况
 * This is a cycle detection in directed graph problem.
 *
 * http://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 * Given a directed graph, check whether the graph contains a cycle or not.
 * Your function should return true if the given graph contains at least one cycle, else return false.
 * For example, the following graph contains three cycles 0->2->0, 0->1->2->0 and 3->3, so your function must return true.
 */
public class DetectCycleInDirectedGraph {

    // There is a cycle in a graph only if there is a back edge present in the graph.
    // To detect a back edge, we can keep track of vertices currently in recursion stack of function for DFS traversal.
    // If we reach a vertex that is already in the recursion stack, then there is a cycle in the tree.
    // The edge that connects current vertex to the vertex in the recursion stack is back edge.
    // We have used recStack[] array to keep track of vertices in the recursion stack.
    public static boolean detectCycle(Graph graph) {
        boolean[] visited = new boolean[graph.V];
        boolean[] inStack = new boolean[graph.V];
        for (int i=0; i<graph.V; i++) {
            if (isCyclic(graph, i, visited, inStack)) return true;
        }
        return false;
    }

    // dfs
    private static boolean isCyclic(Graph graph, int v, boolean[] visited, boolean[] inStack) {
        if (!visited[v]) {
            visited[v] = true;
            inStack[v] = true;
            for (int w : graph.adj[v]) {
                if (!visited[w] && isCyclic(graph, w, visited, inStack)) {
                    return true;
                } else if (inStack[w]) {
                    return true;
                }
            }
        }
        inStack[v] = false;
        return false;
    }


    // another version to not only detect cycle, but also returns the cycle elements
    static Stack<Integer> cycle;
    public static void detectCycle2(Graph graph) {
        boolean[] visited = new boolean[graph.V];       // marked[v] = has vertex v been marked?
        boolean[] inStack = new boolean[graph.V];       // inStack[v] = is vertex on the stack?
        int[] edgeTo = new int[graph.V];               // edgeTo[v] = previous vertex on path to v
        Stack<Integer> cycle = null;                    // directed cycle (or null if no such cycle)
        for (int v = 0; v < graph.V; v++) {
            if (!visited[v] && cycle == null) {
                dfs(graph, v, visited, inStack, edgeTo);
            }
        }
        //return cycle;
    }

    // check that algorithm computes either the topological order or finds a directed cycle
    private static void dfs(Graph graph, int v, boolean[] visited, boolean[] inStack, int[] edgeTo) {
        inStack[v] = true;
        visited[v] = true;
        for (int w : graph.adj[v]) {

            // short circuit if directed cycle found
            if (cycle != null) return;

            // found new vertex, so recur
            else if (!visited[w]) {
                edgeTo[w] = v;
                dfs(graph, w, visited, inStack, edgeTo);
            }

            // trace back directed cycle
            else if (inStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check(cycle);
            }
        }
        inStack[v] = false;
    }

    // certify that digraph has a directed cycle if it reports one
    private static boolean check(Stack<Integer> cycle) {

        if (cycle != null) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }


        return true;
    }


    /////////// helper
    public static Graph buildGraph(List<Event> events) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        Map<String, Integer> eventMap = new HashMap<>();
        int id = 0;
        for (Event event : events) {
            if (!eventMap.containsKey(event.winner)) {
                eventMap.put(event.winner, id++);
            }
            if (!eventMap.containsKey(event.loser)) {
                eventMap.put(event.loser, id++);
            }
            if (!adjMap.containsKey(eventMap.get(event.winner))) {
                adjMap.put(eventMap.get(event.winner), new ArrayList<>());
            }
            adjMap.get(eventMap.get(event.winner)).add(eventMap.get(event.loser));
        }

        // make sure vertices that don't have out edges are initiated as well
        List<Integer>[] adj = new List[eventMap.size()];
        for (int eventId : eventMap.values()) {
            adj[eventId] = adjMap.getOrDefault(eventId, new ArrayList<>());
        }
        return new Graph(adj);
    }


    static class Graph {
        int V;
        List<Integer>[] adj;

        public Graph(List<Integer>[] adj) {
            this.adj = adj;
            V = adj.length;
        }
    }

    static class Event {
        String winner;
        String loser;

        public Event(String winner, String loser) {
            this.winner = winner;
            this.loser = loser;
        }
    }

    public static void main(String[] args) {
        Event e1 = new Event("A", "B");
        Event e2 = new Event("B", "C");
        Event e3 = new Event("C", "A");
        Graph graph = buildGraph(Arrays.asList(e1, e2, e3));
        detectCycle2(graph);
        while (!cycle.empty()) {
            System.out.println(cycle.pop());
        }
    }
}
