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
     * contains keywords.
     */
    public static final int TRUE = 1;
    
    /**
     * not contain keywords.
     */
    public static final int FALSE = 0;
    
    /**
     * error occurred.
     */
    public static final int ERROR = -1;
    
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
     * @return ERROR, TRUE, FALSE.
     */
    public int contains(String str) {
        if (null == str) {
            return ERROR;
        }
        try {
            boolean contains = ahoCorasickAutomaton.contains(str.toLowerCase());
            LOGGER.info("KeywordSearch:{},{}", str, contains);
            return contains ? TRUE : FALSE;
        } catch (Exception e) {
            LOGGER.error("KeywordSearch:{},{}", str, e);
            return ERROR;
        }
    }
    
}
