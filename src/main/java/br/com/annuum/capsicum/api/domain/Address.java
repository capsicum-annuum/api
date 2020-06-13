package br.com.annuum.capsicum.api.domain;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
public class Address {

    @Id
    @GeneratedValue(generator = "address_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @Column(columnDefinition = "geography(POINT, 4326)")
    private Point geolocation;

    @NotNull
    @ManyToOne
    private City city;

    private String district;

    private String streetName;

    private String addressNumber;

    private String complement;

}
