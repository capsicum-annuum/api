package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private UserVolunteer userCandidate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CandidacyStatus candidacyStatus;

    @CreatedDate
    private LocalDateTime createdAt;

}
