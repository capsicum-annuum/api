package br.com.annuum.capsicum.api.domain;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence")
public class Address {

    @Id
    @GeneratedValue(generator = "address_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(columnDefinition = "geography(POINT, 4326)")
    private Point geolocation;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @ManyToOne
    private City city;

    private String googlePlaceAddressId;

    private String district;

    private String streetName;

    private String addressNumber;

    private String complement;
}
