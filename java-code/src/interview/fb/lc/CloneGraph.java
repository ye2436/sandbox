package interview.fb.lc;

import java.util.*;

/**
 * #133. Clone Graph
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *      1
 *     / \
 *    /   \
 *   0 --- 2
 *        / \
 *        \_/
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraph_dfs_practice(UndirectedGraphNode node) {
        return clone(node, new HashMap<>());
    }

    // visited map : old node -> cloned node
    public UndirectedGraphNode clone(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> visited) {
        if (node == null) return null;
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        visited.put(node, cloned);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            cloned.neighbors.add(clone(neighbor, visited));
        }
        return cloned;
    }

    public UndirectedGraphNode cloneGraph_bfs_practice(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
        visited.put(node, new UndirectedGraphNode(node.label));
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            // now check neighbors
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    queue.offer(neighbor);
                }
                visited.get(curr).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }


    // BFS
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
        //UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        queue.add(node);
        visited.put(node, new UndirectedGraphNode(node.label));

        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.pop();
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    //cloned = new UndirectedGraphNode(neighbor.label);
                    visited.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    queue.add(neighbor);
                }
                visited.get(curr).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }


    // A depth-first search (DFS) is an algorithm for traversing a finite graph. DFS visits the child vertices before visiting the sibling vertices;
    // that is, it traverses the depth of any particular path before exploring its breadth. A stack (often the program's call stack via recursion)
    // is generally used when implementing the algorithm.
    // The algorithm begins with a chosen "root" vertex; it then iteratively transitions from the current vertex to an adjacent, unvisited vertex,
    // until it can no longer find an unexplored vertex to transition to from its current location. The algorithm then backtracks along previously
    // visited vertices, until it finds a vertex connected to yet more uncharted territory. It will then proceed down the new path as it had before,
    // backtracking as it encounters dead-ends, and ending only when the algorithm has backtracked past the original "root" vertex from the very first step.
    // DFS is the basis for many graph-related algorithms, including topological sorts and planarity testing.
    // Pseudocode:
    // Input: A graph G and a vertex v of G.
    // Output: A labeling of the edges in the connected component of v as discovery edges and back edges.
    //  procedure DFS(G, v):
    //      label v as explored
    //      for all edges e in G.incidentEdges(v) do
    //          if edge e is unexplored then
    //              w ← G.adjacentVertex(v, e)
    //              if vertex w is unexplored then
    //                  label e as a discovered edge
    //                  recursively call DFS(G, w)
    //              else
    //                  label e as a back edge
    // Source: https://en.wikipedia.org/wiki/Graph_traversal#Depth-first_search
    public static UndirectedGraphNode cloneGraph_2(UndirectedGraphNode node) {
        if (node == null) return null;

        Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
        visited.put(node, new UndirectedGraphNode(node.label));
        dfs(node, visited);
        return visited.get(node);
    }

    private static void dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> visited) {
        for (UndirectedGraphNode neighbor : node.neighbors) {
            if (!visited.containsKey(neighbor)) {
                visited.put(neighbor, new UndirectedGraphNode(neighbor.label));
                dfs(neighbor, visited);
            }
            visited.get(node).neighbors.add(visited.get(neighbor));
        }
    }

    public static void main (String[] args) {
        UndirectedGraphNode node = generate();
        UndirectedGraphNode cloned = cloneGraph(node);
        System.out.println(print(node));
        System.out.println(print(cloned));
    }

    private static UndirectedGraphNode generate() {
        UndirectedGraphNode n0 = new UndirectedGraphNode(0);
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        UndirectedGraphNode n2 = new UndirectedGraphNode(2);
        n0.neighbors = Arrays.asList(n1,n2);
        n1.neighbors = Arrays.asList(n2);
        n2.neighbors = Arrays.asList(n2);
        return n0;
    }

    private static String print(UndirectedGraphNode node) {
        String s0 = String.valueOf(node.label);
        List<String> neighborList = new ArrayList<>();
        for (UndirectedGraphNode neighbor : node.neighbors) {
            s0 += "," + neighbor.label;
            String s_n = String.valueOf(neighbor.label);
            for (UndirectedGraphNode child : neighbor.neighbors) {
                s_n += "," + child.label;
            }
            neighborList.add(s_n);
        }
        return s0 +"#"+ String.join("#", neighborList);
    }

    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<>(); }
    }
}
