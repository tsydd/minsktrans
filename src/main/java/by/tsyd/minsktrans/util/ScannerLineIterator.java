package by.tsyd.minsktrans.util;

import com.google.common.collect.AbstractIterator;

import java.util.Scanner;

/**
* @author Dmitry Tsydzik
* @since Date: 27.02.14.
*/
public class ScannerLineIterator extends AbstractIterator<String> {
    private final Scanner scanner;

    public ScannerLineIterator(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    protected String computeNext() {
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            return line.isEmpty() ? endOfData() : line;
        } else {
            return endOfData();
        }
    }
}
