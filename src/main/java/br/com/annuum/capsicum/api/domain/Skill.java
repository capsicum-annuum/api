package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "skill_sequence", sequenceName = "skill_sequence", allocationSize = 1)
public class Skill {

    @Id
    @GeneratedValue(generator = "skill_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    private Integer binaryIdentifier;
}
