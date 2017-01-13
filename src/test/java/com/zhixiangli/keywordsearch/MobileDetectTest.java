/**
 * 
 */
package com.zhixiangli.keywordsearch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zhixiangli.keywordsearch.examples.MobileDetect;

/**
 * test of mobile detect.
 * 
 * @author lizhixiang
 *
 */
public class MobileDetectTest {

    @Test
    public void testIsMobile() {
        String[] userAgent = new String[] {"Mozilla/5.0 (Windows NT 6.1; rv:19.0) Gecko/20100101 Firefox/19.0",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA; .NET CLR 2.0.50727)",
                "Mozilla/5.0 (Linux; U; Android 4.1; zh-cn; Lenovo-P770/S100) AppleWebKit/534.30 (KHTML, like Gecko)",
                "Mozilla/5.0 (iPad; CPU OS 5_1_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Mobile/9B206",
                "UCWEB/2.0 (iOS; U; iPh OS 6_1_1; zh-CN; iPh4,1) U2/1.0.0 UCBrowser/9.0.1.284 U2/1.0.0 Mobile",};
        boolean[] answer = new boolean[] {false, false, true, true, true};
        for (int i = 0; i < userAgent.length; ++i) {
            boolean isMobile = MobileDetect.isMobile(userAgent[i]);
            assertEquals(answer[i], isMobile);
        }
    }

}
