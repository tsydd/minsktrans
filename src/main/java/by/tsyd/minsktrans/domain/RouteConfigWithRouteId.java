package by.tsyd.minsktrans.domain;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.03.14.
 */
public class RouteConfigWithRouteId {
    private Long routeId;
    private RouteTimeConfig config;

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public RouteTimeConfig getConfig() {
        return config;
    }

    public void setConfig(RouteTimeConfig config) {
        this.config = config;
    }
}
