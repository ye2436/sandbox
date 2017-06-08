package interview.am.other;

import java.util.*;

/**
 * list {(parent1, child1), (parent1, child2), (parent2, child3), (parent2, child4)} construct binary tree
 *
 *
 * Given a list of child->parent relationships, build a binary tree out of it. All the element Ids inside the tree are unique.

 Example:

 Given the following relationships:

 Child Parent IsLeft
 15 20 true
 19 80 true
 17 20 false
 16 80 false
 80 50 false
 50 null false
 20 50 true

 You should return the following tree:

        50
       /  \
      20   80
     / \   / \
    15 17 19 16

 */
public class ConstructBinaryTree {

    // the idea is as we are reading the list, save off the tree node as we build them.
    // Store them in a hash map (id -> node)
    // root node will be the one where parent = null

    /**
     * Implement a method to build a tree from a list of parent-child relationships
     * And return the root Node of the tree
     */
    Node buildTree (List<Relation> data) {
        if (data == null) return null;
        Map<Integer, Node> nodeMap = new HashMap<>();
        Node root = null;
        for (Relation relation : data) {

            if (relation.parent == null) {
                root = getNode(relation.child, nodeMap);
                continue;
            }

            Node parent = getNode(relation.parent, nodeMap);
            Node child = getNode(relation.child, nodeMap);
            if (relation.isLeft) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        return root;
    }

    private Node getNode(int val, Map<Integer, Node> nodeMap) {
        if (!nodeMap.containsKey(val)) {
            nodeMap.put(val, new Node(val));
        }
        return nodeMap.get(val);
    }

    public static void main(String[] args) {
        ConstructBinaryTree obj = new ConstructBinaryTree();
        List<Relation> data = obj.buildRelations();
        Node root = obj.buildTree(data);
        System.out.println(root);
    }

    // for testing
    List<Relation> buildRelations() {
        List<Relation> data = new ArrayList<>();
        data.add(new Relation(20, 15, true));
        data.add(new Relation(80, 19, true));
        data.add(new Relation(20, 17, false));
        data.add(new Relation(80, 16, false));
        data.add(new Relation(50, 80, false));
        data.add(new Relation(null, 50, false));
        data.add(new Relation(50, 20, true));
        return data;
    }

    /**
     * Represents a pair relation between one parent node and one child node inside a binary tree
     * If the _parent is null, it represents the ROOT node
     */
    class Relation {
        Integer parent;
        Integer child;
        boolean isLeft;

        public Relation(Integer parent, Integer child, boolean isLeft) {
            this.parent = parent;
            this.child = child;
            this.isLeft = isLeft;
        }
    }


    /**
     * Represents a single Node inside a binary tree
     */
    class Node {
        int id;
        Node left;
        Node right;
        public Node(int id) {
            this.id = id;
        }
    }
}
