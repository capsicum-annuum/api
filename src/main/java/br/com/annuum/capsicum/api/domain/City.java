package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.State;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence")
public class City {

    @Id
    @GeneratedValue(generator = "city_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String googlePlaceCityId;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private State state;
}
