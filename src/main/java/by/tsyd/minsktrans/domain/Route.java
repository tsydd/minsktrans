package by.tsyd.minsktrans.domain;

import java.util.Collections;
import java.util.List;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class Route {
    private Long id;
    private String routeNumber;
    private TransportType transport;
    private String operator;
    private String routeType;
    private String routeName;
    private List<Stop> stops = Collections.emptyList();

    @Override
    public String toString() {
        return "Route{" +
                "transport=" + transport +
                ", routeNumber='" + routeNumber + '\'' +
                ", routeType='" + routeType + '\'' +
                ", routeName='" + routeName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
