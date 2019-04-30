/**
 *
 */
package com.zhixiangli.keywordsearch;

import com.google.common.base.Preconditions;

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
     * @param characterRange the range of character.
     */
    public AhoCorasickAutomaton(final int characterRange) {
        this.characterRange = characterRange;
    }

    /**
     *
     * clear the trie.
     */
    public void clear() {
        root = null;
    }

    /**
     *
     * initialization.
     */
    public void init() {
        root = new TrieNode();
        root.fail = root;

        // root.next is null when root is initialized.
        Arrays.fill(root.next, root);
    }

    /**
     *
     * add all the string to trie.
     *
     * @param stringArray string array.
     */
    public void add(final CharSequence[] stringArray) {
        Preconditions.checkNotNull(stringArray);
        Arrays.stream(stringArray).forEach(this::add);
    }

    /**
     *
     * add the char sequence to trie.
     *
     * @param charSequence char sequence.
     */
    public void add(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        TrieNode current = root;
        for (int i = 0; i < charSequence.length(); ++i) {
            final int j = charSequence.charAt(i);
            Preconditions.checkArgument(j < characterRange);
            if (root == current.next[j]) {
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
     * @param charSequence char sequence.
     * @return true if this charSequence contains some string.
     */
    public boolean contains(final CharSequence charSequence) {
        if (null == charSequence) {
            return false;
        }
        TrieNode current = root;
        for (int i = 0; i < charSequence.length(); ++i) {
            final int j = charSequence.charAt(i);
            Preconditions.checkArgument(j < characterRange);
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
        final Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final TrieNode current = queue.poll();
            for (int i = 0; i < characterRange; ++i) {
                if (root != current.next[i]) {
                    final TrieNode next = current.next[i];
                    queue.add(next);
                    if (current != root) {
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
            isEnd = false;
            fail = root;
            next = new TrieNode[characterRange];
            if (null != root) {
                Arrays.fill(next, root);
            }
        }

    }

}
