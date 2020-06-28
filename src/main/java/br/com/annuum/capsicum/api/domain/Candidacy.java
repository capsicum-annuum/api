package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "candidacy_sequence", sequenceName = "candidacy_sequence", allocationSize = 1)
public class Candidacy {

    @Id
    @GeneratedValue(generator = "candidacy_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Need need;

    @ManyToOne
    private UserVolunteer userCandidate;

    @Embedded
    private CandidacyStatusControl candidacyStatusControl;

    @CreatedDate
    private LocalDateTime createdAt;

}
