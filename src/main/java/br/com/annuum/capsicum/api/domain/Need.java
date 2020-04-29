package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "need_sequence", sequenceName = "need_sequence", allocationSize = 1)
public class Need {

    @Id
    @GeneratedValue(generator = "need_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne
    private Skill skill;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String description;

    @ManyToMany
    private List<Candidate> candidates;

    @NotNull
    private Boolean isActive;

}
