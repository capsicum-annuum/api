package br.com.annuum.capsicum.api.component;

import br.com.annuum.capsicum.api.domain.SpatialLocation;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.stereotype.Component;

@Component
public class PointFactory extends GeometryFactory {

    public Point createPointFromSpatialLocation(SpatialLocation spatialLocation) {

        final Coordinate coordinate = new Coordinate(spatialLocation.getLatitude(), spatialLocation.getLongitude());

        return createPoint(coordinate);
    }

}
