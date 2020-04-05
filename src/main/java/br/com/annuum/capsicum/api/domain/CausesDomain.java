package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "causes_sequence", sequenceName = "causes_sequence")
@Table(name = "causes")
public class CausesDomain {

    @Id
    private Long id;

    private String description;
}
