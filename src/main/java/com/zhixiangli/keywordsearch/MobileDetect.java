/**
 * 
 */
package com.zhixiangli.keywordsearch;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mobile detect.
 * 
 * @author lizhixiang
 *
 */
public class MobileDetect {
    
    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MobileDetect.class);
    
    /**
     * keyword search class.
     */
    private static final KeywordSearch KEYWORD_SEARCH = new KeywordSearch();
    
    static {
        try {
            String[] mobileKeywords = new String[] { "android", "webos", "iphone", "ipad", "ipod", "pocket", "psp",
                "kindle", "avantgo", "blazer", "midori", "tablet", "palm", "maemo", "plucker", "phone", "blackberry",
                "symbian", "iemobile", "mobile", "zunewp7", "windows phone", "opera mini", };
            KEYWORD_SEARCH.add(Stream.of(mobileKeywords).map(String::toLowerCase).distinct()
                .collect(Collectors.toList()));
        } catch (Exception e) {
            LOGGER.error("MobileDetect failed to initialize: {}", e);
        }
    }
    
    /**
     * 
     * the user agent is from mobile?
     * 
     * @param userAgent
     *            user agent.
     * @return ERROR, TRUE, FALSE.
     */
    public static Result isMobile(String userAgent) {
        return KEYWORD_SEARCH.contains(userAgent);
    }
    
}
