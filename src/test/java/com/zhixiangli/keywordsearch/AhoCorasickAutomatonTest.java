package com.zhixiangli.keywordsearch;

import static org.junit.Assert.assertEquals;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Stopwatch;

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
        long indexOfTotal = 0;
        long acTotal = 0;
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

            // first way: indexOf.
            Stopwatch stopwatch = Stopwatch.createStarted();
            boolean result0 = false;
            for (int i = 0; i < keywords.length; ++i) {
                if (sb.indexOf(keywords[i]) >= 0) {
                    result0 = true;
                    break;
                }
            }
            stopwatch.stop();
            indexOfTotal += stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("indexOf: " + stopwatch);

            // second way: ac automaton.
            ac.init();
            ac.add(keywords);
            ac.build();
            stopwatch.reset();
            stopwatch.start();
            boolean result1 = ac.contains(sb);
            stopwatch.stop();
            acTotal += stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("ac: " + stopwatch);

            // output.
            assertEquals(result1, result0);

        }
        System.out.println(1.0 * indexOfTotal / acTotal + " times faster");

    }

}
