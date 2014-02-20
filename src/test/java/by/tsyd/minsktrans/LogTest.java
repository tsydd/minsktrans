package by.tsyd.minsktrans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * @author Dmitry Tsydzik
 * @since Date: 16.02.14.
 */
public class LogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() throws Exception {
        LOGGER.trace("trace");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.debug("debug");
        LOGGER.error("error");
    }
}
