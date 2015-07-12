/**
 * 
 */
package com.zhixiangli.keywordsearch;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * whether a string contains keyword.
 * 
 * @author lizhixiang
 *
 */
public class KeywordSearch {
    
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordSearch.class);
    
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
     * @param characterRange
     *            the character range of keywords.
     */
    public KeywordSearch(int characterRange) {
        this.ahoCorasickAutomaton = new AhoCorasickAutomaton(characterRange);
    }
    
    /**
     * 
     * add all the string to trie.
     * 
     * @param stringArray
     *            string array.
     */
    public void add(CharSequence[] stringArray) {
        this.ahoCorasickAutomaton.init();
        Arrays.stream(stringArray).forEach(str -> this.ahoCorasickAutomaton.add(str));
        this.ahoCorasickAutomaton.build();
    }
    
    /**
     * 
     * add all the string to trie.
     * 
     * @param stringCollection
     *            string collection.
     */
    public void add(Collection<? extends CharSequence> stringCollection) {
        this.add(stringCollection.stream().toArray(CharSequence[]::new));
    }
    
    /**
     * 
     * whether the substring of str is in the keywords.
     * 
     * @param str
     *            string to be checked.
     * @return ERROR, NOT_CONTAIN, CONTAIN.
     */
    public Result contains(String str) {
        if (null == str) {
            return Result.NOT_CONTAIN;
        }
        try {
            boolean contains = ahoCorasickAutomaton.contains(str.toLowerCase());
            LOGGER.info("KeywordSearch result: {},{}", str, contains);
            return contains ? Result.CONTAIN : Result.NOT_CONTAIN;
        } catch (Exception e) {
            LOGGER.error("KeywordSearch error: {},{}", str, e);
            return Result.ERROR;
        }
    }
    
}
