package by.tsyd.minsktrans.csv.stop;

import by.tsyd.minsktrans.ResourceProvider;
import by.tsyd.minsktrans.csv.GenericCsvDataSupplier;
import com.google.common.collect.ImmutableMap;

import java.util.function.Consumer;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.02.14
 */
public class StopCsvListSupplier extends GenericCsvDataSupplier<StopCsv> {

    public StopCsvListSupplier(ResourceProvider resourceProvider) {
        super(resourceProvider,
                StopCsv::new,
                stopCsv -> ImmutableMap.<String, Consumer<String>>builder()
                        .put("ID", id -> stopCsv.setId(Long.valueOf(id)))
                        .put("City", stopCsv::setCity)
                        .put("Area", stopCsv::setArea)
                        .put("Street", stopCsv::setStreet)
                        .put("Name", stopCsv::setName)
                        .put("Info", stopCsv::setInfo)
                        .put("Lat", stopCsv::setLatitude)
                        .put("Lng", stopCsv::setLongitude)
                        .put("Stops", stopCsv::setStops)
                        .put("StopNum", stopCsv::setStopNum)
                        .build());
    }
}
