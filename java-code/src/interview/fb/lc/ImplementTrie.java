package interview.fb.lc;

import java.util.HashMap;
import java.util.Map;

/**
 *  208. Implement Trie (Prefix Tree)
 *  Implement a trie with insert, search, and startsWith methods.
 *  Note:
 *  You may assume that all inputs are consist of lowercase letters a-z.
 */
public class ImplementTrie {

    // Each node represents a character in the string. So search time complexity is O(M), where M is the search string length
    // We also need a isLeaf parameter in the Node class. To quickly identify the bottom of the tree.
    // * Search word has 1 extra step than search prefix -- it needs to verify if the last node is a leaf node
    // * We default isLeaf to false, only update it to true at the end of inserting a new word
    // so we update once of each word insert, instead of update m-1 times

    public static class Trie {
        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode current = root;
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (current.children[c-'a'] == null) {
                    TrieNode child = new TrieNode();
                    current.children[c-'a'] = child;
                }
                current = current.children[c-'a'];
            }
            // Set isLeaf/isEnd at the end
            current.isLeaf = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode current = root;
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (current.children[c-'a'] == null) { // no need to check if curr is leaf here. if it's leaf, its children is always null
                    return false;
                }
                current = current.children[c-'a'];
            }
            return current.isLeaf;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode current = root;
            for (int i=0; i<prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (current.children[c-'a'] == null) {
                    return false;
                }
                current = current.children[c-'a'];
            }
            return true;
        }

        class TrieNode {
            //Character value; // no need for a value. it can be inferred from its parent's index
            TrieNode[] children; // index 0: a, index 25: z
            boolean isLeaf; // true if the node represents end of a word
            public TrieNode() {
                children = new TrieNode[26];
            }
        }
    }

    // Implementation with hash map
    public static class Trie2 {
        TrieNode root;

        /** Initialize your data structure here. */
        public Trie2() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode curr = root;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
            }
            curr.isLeaf = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            TrieNode curr = root;
            for (char c : chars) {
                if (!curr.children.containsKey(c)) return false;
                curr = curr.children.get(c);
            }
            return curr.isLeaf;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            TrieNode curr = root;
            for (char c : chars) {
                if (!curr.children.containsKey(c)) return false;
                curr = curr.children.get(c);
            }
            return true;
        }

        class TrieNode {
            // use map to suit utf-8 characters
            Map<Character, TrieNode> children;
            boolean isLeaf; // true if the node represents end of a word
            public TrieNode() {
                children = new HashMap<>();
            }
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    public static void main(String[] args) {
        Trie2 obj = new Trie2();
        obj.insert("abc");
        System.out.println(obj.search("abc"));
        System.out.println(obj.search("ab"));
        obj.insert("ab");
        System.out.println(obj.search("ab"));
        System.out.println("######");

        obj = new Trie2();
        obj.insert("ab");
        System.out.println(obj.search("abc")); // F
        System.out.println(obj.search("ab")); // T
        System.out.println(obj.startsWith("abc")); // F
        System.out.println(obj.startsWith("ab")); // T
        obj.insert("ab");
        System.out.println(obj.search("abc")); // F
        System.out.println(obj.startsWith("abc")); // F
        obj.insert("abc");
        System.out.println(obj.search("abc")); // T
        System.out.println(obj.startsWith("abc")); // T

    }
}
