public class Trie {

    Node root;

    public class Node {
        Node[] children = new Node[26];
        boolean endOfWord;
        public Node() {}
    }

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node newNode = insertHelper(root, word, 0);
        newNode.endOfWord = true;
    }

    private Node insertHelper(Node node, String word, int i) {
        if (i == word.length()) {
            return node;
        }
        int index = word.charAt(i) - 'a';
        if (node.children[index] == null) {
            node.children[index] = new Node();
        }
        return insertHelper(node.children[index], word, i + 1);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node node = searchHelper(root, word, 0);
        if (node != null && node.endOfWord) {
            return true;
        }
        return false;
    }

    private Node searchHelper(Node node, String word, int i) {
        if (i == word.length()) {
            return node;
        }
        int index = word.charAt(i) - 'a';
        if (node.children[index] == null) {
            return null;
        }
        return searchHelper(node.children[index], word, i + 1);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        boolean found = (searchHelper(root, prefix, 0) != null);
        return found;
    }

    public boolean delete(String word) {
        Node node = deleteHelper(root, word, 0);
        if (node != null) {
            node.endOfWord = false;
            return true;
        } else {
            return false;
        }
    }

    private Node deleteHelper(Node node, String word, int i) {
        if (i == word.length()) {
            return node;
        }
        int index = word.charAt(i) - 'a';
        if (node.children[index] == null) {
            return null;
        }
        return deleteHelper(node.children[index], word, i + 1);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */