package com.zhixiangli.keywordsearch;

import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Random;

import org.junit.Test;

/**
 * 
 */

/**
 * test of Aho-Corasick automaton.
 * 
 * @author lizhixiang
 *
 */
public class AhoCorasickAutomatonTest {
    
    private static final Random RANDOM = new SecureRandom();
    
    @Test
    public void test() {
        AhoCorasickAutomaton ac = new AhoCorasickAutomaton();
        
        // number of test case.
        int cnt = Short.MAX_VALUE;
        while (cnt-- > 0) {
            
            // keywords.
            String[] keywords = new String[RANDOM.nextInt(Short.MAX_VALUE) + 1];
            for (int i = 0; i < keywords.length; ++i) {
                keywords[i] = String.valueOf(RANDOM.nextInt(Integer.MAX_VALUE));
            }
            
            // char sequence.
            StringBuilder sb = new StringBuilder();
            int length = RANDOM.nextInt(Short.MAX_VALUE) + 1;
            for (int i = 0; i < length; ++i) {
                sb.append(RANDOM.nextInt(10));
            }
            
            // timer.
            long st;
            long nd;
            
            // first way: indexOf.
            boolean flag0 = false;
            st = System.nanoTime();
            for (int i = 0; i < keywords.length; ++i) {
                if (sb.indexOf(keywords[i]) >= 0) {
                    flag0 = true;
                    break;
                }
            }
            nd = System.nanoTime();
            long t0 = nd - st;
            
            // second way: ac automaton.
            ac.add(keywords);
            st = System.nanoTime();
            boolean flag1 = ac.contains(sb);
            nd = System.nanoTime();
            long t1 = nd - st;
            
            // output.
            assertEquals(flag1, flag0);
            System.out.println(String.format("time: %d, result: %d", t0 / t1, flag1 ? 1 : 0));
        }
    }
    
}
