package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Accessors(chain = true)
@Entity
public class City {

    @Id
    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;

    private Boolean capital;

    @ManyToOne
    private FederatedUnity federatedUnity;
}
