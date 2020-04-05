package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "user_address_sequence", sequenceName = "user_address_sequence")
@Table(name = "user_address")
public class UserAddressDomain {

    @Id
    @GeneratedValue(generator = "user_address_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String streetName;

    private String district;

    private String addressNumber;

    private String complement;

    @ManyToOne
    private CityDomain city;

    private String cep;
}
