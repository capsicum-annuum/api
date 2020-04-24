package br.com.annuum.capsicum.api.component;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.springframework.stereotype.Component;

@Component
public class PointFactory extends GeometryFactory {

    public Point createPointFromCoordinates(double latitude, double longitude) {

        final Coordinate coordinate = new Coordinate(latitude, longitude);

        return createPoint(coordinate);
    }

}
