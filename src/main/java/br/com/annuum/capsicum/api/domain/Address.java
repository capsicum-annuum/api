package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence")
public class Address {

    @Id
    @GeneratedValue(generator = "address_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String googlePlaceAddressId;

    @Embedded
    private LocationCoordinates LocationCoordinates;

    @NotBlank
    @ManyToOne
    private City city;

    private String district;

    private String streetName;

    private String addressNumber;

    private String complement;
}
