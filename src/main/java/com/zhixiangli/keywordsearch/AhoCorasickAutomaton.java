/**
 * 
 */
package com.zhixiangli.keywordsearch;

import java.util.Arrays;
import java.util.Collection;
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
     * default character range of trie.
     */
    private int characterRange = Byte.MAX_VALUE + 1;
    
    /**
     * if locked, characterRange can not be changed.
     */
    private boolean isLocked = false;
    
    /**
     * root of trie.
     */
    private TrieNode root = null;
    
    /**
     * 
     * clear the trie.
     */
    public void clear() {
        this.root = null;
        this.isLocked = false;
    }
    
    /**
     * 
     * clear and add all the string to trie.
     * 
     * @param stringArray
     *            string array.
     */
    public void add(CharSequence[] stringArray) {
        this.init();
        if (null != stringArray) {
            for (int i = 0; i < stringArray.length; ++i) {
                this.add(stringArray[i]);
            }
        }
        this.build();
    }
    
    /**
     * 
     * clear and add all the string to trie.
     * 
     * @param stringCollection
     *            string collection.
     */
    public void add(Collection<CharSequence> stringCollection) {
        this.add(stringCollection.toArray(new CharSequence[0]));
    }
    
    /**
     * 
     * whether char sequence contains some string in trie.
     * 
     * @param charSequence
     *            char sequence.
     * @return true if this charSequence contains some string.
     */
    public boolean contains(CharSequence charSequence) {
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
     * initialization.
     */
    private void init() {
        this.isLocked = true;
        this.root = new TrieNode();
        this.root.fail = this.root;
        Arrays.fill(this.root.next, this.root);
    }
    
    /**
     * 
     * add the char sequence to trie.
     * 
     * @param charSequence
     *            char sequence.
     */
    private void add(CharSequence charSequence) {
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
     * build fail pointer.
     */
    private void build() {
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
     * setter method for property characterRange
     * 
     * @param characterRange
     *            the characterRange to set
     * @throws IllegalAccessException
     *             if is locked.
     */
    public void setCharacterRange(int characterRange) throws IllegalAccessException {
        if (this.isLocked) {
            throw new IllegalAccessException();
        }
        if (characterRange <= 0) {
            throw new IllegalArgumentException();
        }
        this.characterRange = characterRange;
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
            Arrays.fill(this.next, root);
        }
        
    }
    
}
