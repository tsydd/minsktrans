package by.tsyd.minsktrans.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.02.14
 */
public class HttpCsvInputStreamSupplier implements Supplier<InputStream> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpCsvInputStreamSupplier.class);
    private final String urlString;

    public HttpCsvInputStreamSupplier(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public InputStream get() {
        try {
            URLConnection connection = new URL(urlString).openConnection();
            InputStream stream = connection.getInputStream();
            //noinspection ResultOfMethodCallIgnored
            stream.read(new byte[3]);
            return stream;
        } catch (IOException e) {
            LOGGER.error("Failed to read data", e);
            throw new RuntimeException(e);
        }
    }
}
