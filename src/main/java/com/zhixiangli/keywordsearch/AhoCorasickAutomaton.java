/**
 * 
 */
package com.zhixiangli.keywordsearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * implement of Aho-Corasick automaton.
 * 
 * @author lizhixiang
 *
 */
public class AhoCorasickAutomaton {
    
    /**
     * character range of all the string.
     */
    private final int characterRange;
    
    /**
     * root of trie.
     */
    private TrieNode root = null;
    
    /**
     * constructor.
     */
    public AhoCorasickAutomaton() {
        this(1 << 8);
    }
    
    /**
     * @param characterRange
     *            the range of character.
     */
    public AhoCorasickAutomaton(int characterRange) {
        this.characterRange = characterRange;
    }
    
    /**
     * 
     * clear the trie.
     */
    public void clear() {
        this.root = null;
    }
    
    /**
     * 
     * initialization.
     */
    public void init() {
        this.root = new TrieNode();
        this.root.fail = this.root;
        
        // root.next is null when root is initialized.
        Arrays.fill(this.root.next, this.root);
    }
    
    /**
     * 
     * add all the string to trie.
     * 
     * @param stringArray
     *            string array.
     */
    public void add(CharSequence[] stringArray) {
        if (null == stringArray) {
            throw new IllegalArgumentException();
        }
        Arrays.stream(stringArray).forEach(str -> this.add(str));
    }
    
    /**
     * 
     * add the char sequence to trie.
     * 
     * @param charSequence
     *            char sequence.
     */
    public void add(CharSequence charSequence) {
        if (null == charSequence) {
            return;
        }
        TrieNode current = this.root;
        for (int i = 0; i < charSequence.length(); ++i) {
            int j = charSequence.charAt(i);
            if (j >= this.characterRange) {
                throw new IllegalArgumentException();
            }
            if (this.root == current.next[j]) {
                current.next[j] = new TrieNode();
            }
            current = current.next[j];
        }
        current.isEnd = true;
    }
    
    /**
     * 
     * whether the char sequence contains some string in trie.
     * 
     * @param charSequence
     *            char sequence.
     * @return true if this charSequence contains some string.
     */
    public boolean contains(CharSequence charSequence) {
        if (null == charSequence) {
            return false;
        }
        TrieNode current = this.root;
        for (int i = 0; i < charSequence.length(); ++i) {
            int j = charSequence.charAt(i);
            if (j >= this.characterRange) {
                throw new IllegalArgumentException();
            }
            current = current.next[j];
            if (current.isEnd) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * build fail pointer.
     */
    public void build() {
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
            for (int i = 0; i < this.characterRange; ++i) {
                if (this.root != current.next[i]) {
                    TrieNode next = current.next[i];
                    queue.add(next);
                    if (current != this.root) {
                        next.fail = current.fail.next[i];
                        next.isEnd = next.isEnd || next.fail.isEnd;
                    }
                } else {
                    current.next[i] = current.fail.next[i];
                }
            }
        }
    }
    
    /**
     * 
     * trie node.
     * 
     * @author lizhixiang
     *
     */
    private class TrieNode {
        
        /**
         * is the end of a string?
         */
        private boolean isEnd;
        
        /**
         * fail pointer.
         */
        private TrieNode fail;
        
        /**
         * next pointer.
         */
        private TrieNode[] next;
        
        /**
         * initialization.
         */
        public TrieNode() {
            this.isEnd = false;
            this.fail = root;
            this.next = new TrieNode[characterRange];
            if (null != root) {
                Arrays.fill(this.next, root);
            }
        }
        
    }
    
}
