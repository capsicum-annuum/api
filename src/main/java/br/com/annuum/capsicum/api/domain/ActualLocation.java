package br.com.annuum.capsicum.api.domain;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Embeddable
public class ActualLocation {

    @NotNull
    private Double actualLatitude;

    @NotNull
    private Double actualLongitude;

    @Column(columnDefinition = "geography(POINT, 4326)")
    private Point actualGeolocation;
}
