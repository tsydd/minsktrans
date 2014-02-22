package by.tsyd.minsktrans.csv;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 23.02.14.
 */
public class GenericCsvDataSupplierTest {
    @Test
    public void test() throws Exception {
        Supplier<List<Dto>> dtoSupplier = new GenericCsvDataSupplier<>(
                () -> new ByteArrayInputStream("ID;EMPTY;NON_EMPTY\n1;;val".getBytes()),
                Dto::new,
                dto -> ImmutableMap.<String, Consumer<String>>builder()
                        .put("ID", dto::setId)
                        .put("EMPTY", dto::setEmpty)
                        .put("NON_EMPTY", dto::setNonEmpty)
                        .build()
        );

        Dto dto = dtoSupplier.get().get(0);
        assertEquals("1", dto.getId());
        assertEquals("", dto.getEmpty());
        assertEquals("val", dto.getNonEmpty());
    }
}

class Dto {
    private String id;
    private String empty;
    private String nonEmpty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getNonEmpty() {
        return nonEmpty;
    }

    public void setNonEmpty(String nonEmpty) {
        this.nonEmpty = nonEmpty;
    }
}
