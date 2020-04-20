package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "cause_sequence", sequenceName = "cause_sequence")
public class Cause {

    @Id
    @GeneratedValue(generator = "cause_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String description;

    private Integer binaryIdentifier;
}
