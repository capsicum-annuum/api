package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence")
@Table(name = "city")
public class CityDomain {

    @Id
    @GeneratedValue(generator = "city_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne(targetEntity = StateDomain.class)
    @JoinColumn(name = "state_id")
    private StateDomain state;
}
