package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "cause_sequence", sequenceName = "cause_sequence")
public class Cause {

    @Id
    private Long id;

    @NotBlank
    private String description;
}
