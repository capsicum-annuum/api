package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "user_skill_sequence", sequenceName = "user_skill_sequence")
@Table(name = "user_skill")
public class UserSkillsDomain {

    @Id
    @GeneratedValue(generator = "user_skill_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

}
