/**
 * 
 */
package com.zhixiangli.keywordsearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * determine the type of device, mobile or pc.
 * 
 * @author lizhixiang
 *
 */
public class MobileDetect {
    
    /**
     * ac automaton.
     */
    private static final AhoCorasickAutomaton AHO_CORASICK_AUTOMATON = new AhoCorasickAutomaton();
    
    /**
     * mobile keywords.
     */
    private static final String[] MOBILE_KEYWORDS = new String[] { "android", "webos", "iphone",
        "ipad", "ipod", "pocket", "psp", "kindle", "avantgo", "blazer", "midori", "tablet", "palm",
        "maemo", "plucker", "phone", "blackberry", "symbian", "iemobile", "mobile", "zunewp7",
        "windows phone", "opera mini" };
    
    static {
        List<String> mobileKeywords = Arrays.stream(MOBILE_KEYWORDS).map(String::toLowerCase)
            .collect(Collectors.toList());
        AHO_CORASICK_AUTOMATON.add(mobileKeywords);
    }
    
    /**
     * 
     * determine the type of device, mobile or pc.
     * 
     * @param userAgent
     *            user agent.
     * @return true if is mobile device.
     */
    public static boolean isMobile(CharSequence userAgent) {
        if (null == userAgent) {
            throw new IllegalArgumentException();
        }
        String userAgentString = null;
        if (String.class.equals(userAgent.getClass())) {
            userAgentString = (String) userAgent;
        } else {
            userAgentString = userAgent.toString();
        }
        return AHO_CORASICK_AUTOMATON.contains(userAgentString.toLowerCase());
    }
    
}
