package br.com.annuum.capsicum.api.domain.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "skill")
@SequenceGenerator(name = "skill_sequence", sequenceName = "skill_sequence")
public class SkillDomain {

    @Id
    @GeneratedValue(generator = "skill_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String name;

    private String description;
}
