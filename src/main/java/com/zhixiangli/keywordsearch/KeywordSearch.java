/**
 *
 */
package com.zhixiangli.keywordsearch;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Collection;

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
        ahoCorasickAutomaton = new AhoCorasickAutomaton();
    }

    /**
     *
     * @param characterRange the character range of keywords.
     */
    public KeywordSearch(final int characterRange) {
        ahoCorasickAutomaton = new AhoCorasickAutomaton(characterRange);
    }

    /**
     *
     * add all the string to trie.
     *
     * @param stringArray string array.
     */
    public void addAll(final CharSequence[] stringArray) {
        ahoCorasickAutomaton.init();
        Arrays.stream(stringArray).forEach(ahoCorasickAutomaton::add);
        ahoCorasickAutomaton.build();
    }

    /**
     *
     * add all the string to trie.
     *
     * @param stringCollection string collection.
     */
    public void addAll(final Collection<? extends CharSequence> stringCollection) {
        addAll(stringCollection.stream().toArray(CharSequence[]::new));
    }

    /**
     *
     * whether the substring of str is in the keywords.
     *
     * @param str string to be checked.
     * @return true if contains, otherwise not contains.
     */
    public boolean contains(final String str) {
        Preconditions.checkNotNull(str);
        return ahoCorasickAutomaton.contains(str.toLowerCase());
    }

}
