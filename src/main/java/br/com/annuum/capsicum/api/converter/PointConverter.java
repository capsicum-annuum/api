package br.com.annuum.capsicum.api.converter;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.domain.SpatialLocation;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PointConverter implements AttributeConverter<SpatialLocation, Point> {

    @Autowired
    private PointFactory pointFactory;

    @Override
    public Point convertToDatabaseColumn(SpatialLocation spatialLocation) {
        return pointFactory.createPointFromSpatialLocation(spatialLocation);
    }

    @Override
    public SpatialLocation convertToEntityAttribute(Point point) {
        return new SpatialLocation()
                .setLatitude(point.getCoordinate().x)
                .setLongitude(point.getCoordinate().y);
    }

}


