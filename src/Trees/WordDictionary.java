package Trees;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode(' ');
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(!node.children.containsKey(c)) 
              node.children.put(c, new TrieNode(c));
            node = node.children.get(c);  
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] chs, int k, TrieNode node) {
        if (k == chs.length) return node.isWord;   
        if (chs[k] != '.') {
            return node.children.containsKey(chs[k]) && match(chs, k + 1, node.children.get(chs[k]));
        } else {
            for (char c : node.children.keySet()) {
                    if (match(chs, k + 1, node.children.get(c))) {
                        return true;
                    }
                }
        }
        return false;
    }
}

class TrieNode {
    public char val;
    public boolean isWord; 
    public Map<Character, TrieNode> children;
    public TrieNode() {}
    TrieNode(char c){
        TrieNode node = new TrieNode();
        node.val = c;
        children = new HashMap<>();
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
