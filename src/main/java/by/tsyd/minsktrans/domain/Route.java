package by.tsyd.minsktrans.domain;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class Route {
    private String routeNumber;
    private TransportType transport;
    private String operator;
    private String routeType;
    private String routeName;
    private Collection<Stop> stops = Collections.emptyList();

    @Override
    public String toString() {
        return "Route{" +
                "transport=" + transport +
                ", routeNumber='" + routeNumber + '\'' +
                ", routeType='" + routeType + '\'' +
                ", routeName='" + routeName + '\'' +
                '}';
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

    public Collection<Stop> getStops() {
        return stops;
    }

    public void setStops(Collection<Stop> stops) {
        this.stops = stops;
    }
}
