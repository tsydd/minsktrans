package by.tsyd.minsktrans;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.02.14
 */
public interface ResourceProvider {
    InputStreamReader getResource() throws IOException;
}
