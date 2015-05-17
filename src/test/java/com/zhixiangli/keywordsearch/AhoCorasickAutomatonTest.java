package com.zhixiangli.keywordsearch;

import static org.junit.Assert.assertEquals;

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
    
    @Test
    public void test() {
        Random random = new SecureRandom();
        
        AhoCorasickAutomaton ac = new AhoCorasickAutomaton();
        
        // number of test case.
        int cnt = Byte.MAX_VALUE;
        while (cnt-- > 0) {
            
            // keywords.
            String[] keywords = new String[random.nextInt(Short.MAX_VALUE) + 1];
            for (int i = 0; i < keywords.length; ++i) {
                keywords[i] = String.valueOf(random.nextInt(Integer.MAX_VALUE));
            }
            
            // char sequence.
            StringBuilder sb = new StringBuilder();
            int length = random.nextInt(Short.MAX_VALUE) + 1;
            for (int i = 0; i < length; ++i) {
                sb.append(random.nextInt(10));
            }
            
            // timer.
            long st;
            long nd;
            
            // first way: indexOf.
            boolean result0 = false;
            st = System.nanoTime();
            for (int i = 0; i < keywords.length; ++i) {
                if (sb.indexOf(keywords[i]) >= 0) {
                    result0 = true;
                    break;
                }
            }
            nd = System.nanoTime();
            long t0 = nd - st;
            
            // second way: ac automaton.
            ac.init();
            ac.add(keywords);
            ac.build();
            st = System.nanoTime();
            boolean result1 = ac.contains(sb);
            nd = System.nanoTime();
            long t1 = nd - st;
            
            // output.
            assertEquals(result1, result0);
            System.out.println(String.format("time: %f, result: %d", 1.0 * t0 / t1, result1 ? 1
                : 0));
        }
        
    }
    
}
