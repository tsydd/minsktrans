package by.tsyd.minsktrans.integration;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.03.14.
 */
@ContextConfiguration(classes = {
        LocalResourcesSupplierConfig.class,
        IntegrationTestConfig.class
})
@Test(groups = "integration")
public abstract class AbstractLocalResourcesIntegrationTest extends AbstractTestNGSpringContextTests {
}
