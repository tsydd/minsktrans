package by.tsyd.minsktrans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.02.14
 */
public class InputStreamBasedResourceProvider implements ResourceProvider {

    private final InputStream inputStream;

    public InputStreamBasedResourceProvider(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStreamReader getResource() throws IOException {
        return new InputStreamReader(inputStream);
    }
}
