package com.zhixiangli.keywordsearch;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

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
        final Random random = new SecureRandom();

        AhoCorasickAutomaton ac = new AhoCorasickAutomaton();

        // number of test case.
        int cnt = Byte.MAX_VALUE;
        long indexOfTotal = 0;
        long acTotal = 0;
        while (cnt-- > 0) {

            // keywords.
            final String[] keywords = new String[random.nextInt(Short.MAX_VALUE) + 1];
            for (int i = 0; i < keywords.length; ++i) {
                keywords[i] = String.valueOf(random.nextInt(Integer.MAX_VALUE));
            }

            // char sequence.
            final StringBuilder sb = new StringBuilder();
            final int length = random.nextInt(Short.MAX_VALUE) + 1;
            for (int i = 0; i < length; ++i) {
                sb.append(random.nextInt(10));
            }

            // first way: indexOf.
            final Stopwatch stopwatch = Stopwatch.createStarted();
            boolean result0 = false;
            for (final String keyword : keywords) {
                if (sb.indexOf(keyword) >= 0) {
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
            final boolean result1 = ac.contains(sb);
            stopwatch.stop();
            acTotal += stopwatch.elapsed(TimeUnit.MICROSECONDS);
            System.out.println("ac: " + stopwatch);

            // output.
            assertEquals(result1, result0);

        }
        System.out.println(1.0 * indexOfTotal / acTotal + " times faster");

    }

}
