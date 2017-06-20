package interview.fb.lc;

/**
 * 211. Add and Search Word - Data structure design

 Design a data structure that supports the following two operations:

 void addWord(word)
 bool search(word)
 search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

 For example:

 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true
 Note:
 You may assume that all words are consist of lowercase letters a-z.

 You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
 */
public class AddAndSearchWord {

    // In search method, use dfs to recursively find if a match can be found.
    public static class WordDictionary {
        TrieNode root;

        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode curr = root;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (curr.children[c-'a'] == null) {
                    curr.children[c-'a'] = new TrieNode();
                }
                curr = curr.children[c-'a'];
            }
            curr.isLeaf = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return match(word.toCharArray(), 0, root);
        }

        private boolean match(char[] chars, int index, TrieNode curr) {
            // at the end of the search string, check if the trie node is leaf
            if (index == chars.length) {
                return curr.isLeaf;
            }

            if (chars[index] != '.') {
                return curr.children[chars[index] - 'a'] != null && match(chars, index+1, curr.children[chars[index] - 'a']);
            }
            for (int i=0; i<curr.children.length; i++) {
                if (curr.children[i] != null) {
                    if (match(chars, index+1, curr.children[i])) {
                        return true;
                    } // do not return false if not matched. We want to check all possible path
                }
            }
            return false;
        }
    }

    static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
         WordDictionary obj = new WordDictionary();
         obj.addWord("");
         boolean param_2 = obj.search("");
    }


}
