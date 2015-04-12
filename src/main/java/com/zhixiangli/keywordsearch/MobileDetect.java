/**
 * 
 */
package com.zhixiangli.keywordsearch;

/**
 * determine the type of mobile device.
 * 
 * @author lizhixiang
 *
 */
public class MobileDetect {
    
    /**
     * ac automaton.
     */
    private static final AhoCorasickAutomaton AC_AUTOMATON = new AhoCorasickAutomaton();
    
    /**
     * keyword of mobile agent.
     */
    private static final String MOBILE_AGENT = "Android|webOS|iPhone|iPad|iPod|pocket|psp|kindle|avantgo|blazer|midori|Tablet|Palm|maemo|plucker|phone|BlackBerry|symbian|IEMobile|mobile|ZuneWP7|Windows Phone|Opera Mini";
    
    static {
        String[] mobileAgent = MOBILE_AGENT.toLowerCase().split("\\|");
        AC_AUTOMATON.add(mobileAgent);
    }
    
    /**
     * 
     * determine the type of mobile device.
     * 
     * @param userAgent
     *            user agent.
     * @return true if is mobile device.
     */
    public static boolean isMobile(CharSequence userAgent) {
        if (null == userAgent) {
            throw new IllegalArgumentException();
        }
        return AC_AUTOMATON.contains(userAgent.toString().toLowerCase());
    }
    
}
