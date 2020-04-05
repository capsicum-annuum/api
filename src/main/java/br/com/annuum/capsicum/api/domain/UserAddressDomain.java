package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "user_address_sequence", sequenceName = "user_address_sequence")
@Table(name = "user_address")
public class UserAddressDomain {

    @Id
    @GeneratedValue(generator = "user_address_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String streetName;

    @NotBlank
    private String district;

    @NotNull
    private String addressNumber;

    private String complement;

    @NotNull
    @ManyToOne
    private CityDomain city;

    @NotNull
    private String cep;
}
