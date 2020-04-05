package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "state_sequence", sequenceName = "state_sequence")
@Table(name = "state")
public class StateDomain {

    @Id
    @GeneratedValue(generator = "state_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String ufCode;
}
