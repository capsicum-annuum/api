package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "state_sequence", sequenceName = "state_sequence")
@Table(name = "state")
public class StateDomain {

    @Id
    @GeneratedValue(generator = "state_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 2, max = 2)
    private String ufCode;
}
