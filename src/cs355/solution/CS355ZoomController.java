package cs355.solution;

/**
 * Created by Daniel on 10/24/14. ;)
 */
public class CS355ZoomController {

    private double zoomLevel;
    private double maxZoom;
    private double minZoom;

    public CS355ZoomController() {
        zoomLevel = 1;
        maxZoom = 4;
        minZoom = .25;
    }

    public double zoomIn() {
        if(zoomLevel < maxZoom)
            zoomLevel *= 2;

        return zoomLevel;
    }

    public double zoomOut() {
        if(zoomLevel > minZoom)
            zoomLevel *= .5;

        return zoomLevel;
    }

    public double getZoomLevel() {
        return zoomLevel;
    }
}
