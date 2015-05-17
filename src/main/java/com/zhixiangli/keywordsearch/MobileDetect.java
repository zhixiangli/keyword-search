/**
 * 
 */
package com.zhixiangli.keywordsearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
            List<String> mobileKeywords = Files
                .lines(Paths.get("src/main/resources/mobile_keywords")).map(String::toLowerCase)
                .distinct().collect(Collectors.toList());
            KEYWORD_SEARCH.add(mobileKeywords);
        } catch (IOException e) {
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
    public static int isMobile(String userAgent) {
        return KEYWORD_SEARCH.contains(userAgent);
    }
    
}
