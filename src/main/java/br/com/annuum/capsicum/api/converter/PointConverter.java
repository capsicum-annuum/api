package br.com.annuum.capsicum.api.converter;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.domain.SpatialLocation;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import javax.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointConverter implements AttributeConverter<SpatialLocation, String> {

    @Autowired
    private PointFactory pointFactory;

    @Override
    public String convertToDatabaseColumn(SpatialLocation spatialLocation) {
        final String geometryType = pointFactory.createPointFromSpatialLocation(spatialLocation).toString();
        return geometryType;
    }

    @Override
    public SpatialLocation convertToEntityAttribute(String column) {
        try {
            Geometry point = new WKTReader().read(column);
            return new SpatialLocation()
                    .setLatitude(point.getCoordinate().x)
                    .setLongitude(point.getCoordinate().y);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  null;
    }

}

