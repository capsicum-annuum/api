package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.listener.SpatialLocated;
import com.vividsolutions.jts.geom.Point;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "point_sequence", sequenceName = "point_sequence")
public class MyPoint implements SpatialLocated {

    @Id
    @GeneratedValue(generator = "point_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Convert(converter = PointConverter.class)
//    @Column(columnDefinition = "geography(POINT, 4326)")
    @Transient
    private SpatialLocation spatialLocation;

    @Column(columnDefinition = "geography(POINT, 4326)")
    private Point point;
}
