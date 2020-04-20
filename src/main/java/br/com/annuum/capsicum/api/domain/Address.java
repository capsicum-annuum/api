package br.com.annuum.capsicum.api.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

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

    @NotNull
    @ManyToOne
    private City city;

    private String district;

    private String streetName;

    private String addressNumber;

    private String complement;
}
