/**
 * 
 */
package com.zhixiangli.keywordsearch;

import java.util.Arrays;
import java.util.Collection;

import com.google.common.base.Preconditions;

/**
 * whether a string contains keyword.
 * 
 * @author lizhixiang
 *
 */
public class KeywordSearch {

    /**
     * ac automaton.
     */
    private final AhoCorasickAutomaton ahoCorasickAutomaton;

    /**
     * constructor.
     */
    public KeywordSearch() {
        this.ahoCorasickAutomaton = new AhoCorasickAutomaton();
    }

    /**
     * 
     * @param characterRange the character range of keywords.
     */
    public KeywordSearch(int characterRange) {
        this.ahoCorasickAutomaton = new AhoCorasickAutomaton(characterRange);
    }

    /**
     * 
     * add all the string to trie.
     * 
     * @param stringArray string array.
     */
    public void addAll(CharSequence[] stringArray) {
        this.ahoCorasickAutomaton.init();
        Arrays.stream(stringArray).forEach(str -> this.ahoCorasickAutomaton.add(str));
        this.ahoCorasickAutomaton.build();
    }

    /**
     * 
     * add all the string to trie.
     * 
     * @param stringCollection string collection.
     */
    public void addAll(Collection<? extends CharSequence> stringCollection) {
        this.addAll(stringCollection.stream().toArray(CharSequence[]::new));
    }

    /**
     * 
     * whether the substring of str is in the keywords.
     * 
     * @param str string to be checked.
     * @return true if contains, otherwise not contains.
     */
    public boolean contains(String str) {
        Preconditions.checkNotNull(str);
        boolean contains = ahoCorasickAutomaton.contains(str.toLowerCase());
        return contains;
    }

}
