package org.simple.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfigUtilTest {

    private final static Logger logger = LoggerFactory.getLogger(AppConfigUtilTest.class);

    @Test
    public void getAppConfig() throws Exception {
        logger.info("config info is {}",AppConfigUtil.getAppConfig());
    }

}