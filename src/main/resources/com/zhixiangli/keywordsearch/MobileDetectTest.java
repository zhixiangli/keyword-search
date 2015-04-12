/**
 * 
 */
package com.zhixiangli.keywordsearch;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TODO
 * 
 * @author lizhixiang
 *
 */
public class MobileDetectTest {
    
    @Test
    public void testIsMobile() {
        assertTrue(!MobileDetect
            .isMobile("Mozilla/5.0 (Windows NT 6.1; rv:19.0) Gecko/20100101 Firefox/19.0"));
        assertTrue(!MobileDetect
            .isMobile("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA; .NET CLR 2.0.50727)"));
        assertTrue(MobileDetect
            .isMobile("Mozilla/5.0 (Linux; U; Android 4.1; zh-cn; Lenovo-P770/S100) AppleWebKit/534.30 (KHTML, like Gecko)"));
        assertTrue(MobileDetect
            .isMobile("Mozilla/5.0 (iPad; CPU OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9B206"));
        assertTrue(MobileDetect
            .isMobile("UCWEB/2.0 (iOS; U; iPh OS 6_1_1; zh-CN; iPh4,1) U2/1.0.0 UCBrowser/9.0.1.284 U2/1.0.0 Mobile"));
    }
    
}
