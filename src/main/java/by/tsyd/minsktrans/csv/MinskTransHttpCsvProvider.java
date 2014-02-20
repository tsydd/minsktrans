package by.tsyd.minsktrans.csv;

import by.tsyd.minsktrans.MinskTransHttpConstants;
import by.tsyd.minsktrans.ResourceProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.02.14
 */
public class MinskTransHttpCsvProvider implements ResourceProvider {

    private final String urlString;

    public MinskTransHttpCsvProvider(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public InputStreamReader getResource() throws IOException {
        URLConnection connection = new URL(urlString).openConnection();
        InputStream stream = connection.getInputStream();
        stream.read(new byte[3]);
        return new InputStreamReader(stream, MinskTransHttpConstants.DEFAULT_ENCODING);
    }
}
