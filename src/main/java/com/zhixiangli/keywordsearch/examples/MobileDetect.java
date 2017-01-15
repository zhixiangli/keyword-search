/**
 * 
 */
package com.zhixiangli.keywordsearch.examples;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zhixiangli.keywordsearch.KeywordSearch;

/**
 * mobile detect.
 * 
 * @author lizhixiang
 *
 */
public class MobileDetect {

    /**
     * keyword search class.
     */
    private static final KeywordSearch KEYWORD_SEARCH = new KeywordSearch();

    static {
        String[] mobileKeywords = new String[] {"android", "webos", "iphone", "ipad", "ipod", "pocket", "psp", "kindle",
                "avantgo", "blazer", "midori", "tablet", "palm", "maemo", "plucker", "phone", "blackberry", "symbian",
                "iemobile", "mobile", "zunewp7", "windows phone", "opera mini",};
        KEYWORD_SEARCH
                .addAll(Stream.of(mobileKeywords).map(String::toLowerCase).distinct().collect(Collectors.toList()));
    }

    /**
     * 
     * the user agent is from mobile?
     * 
     * @param userAgent user agent.
     * @return true if is mobile.
     */
    public static boolean isMobile(String userAgent) {
        return KEYWORD_SEARCH.contains(userAgent);
    }

}
