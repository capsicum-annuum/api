package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "candidate_sequence", sequenceName = "candidate_sequence", allocationSize = 1)
public class Application {

    @Id
    @GeneratedValue(generator = "candidate_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private UserVolunteer userCandidate;

    @NotNull
    private Boolean isApproved;

    @CreatedDate
    private LocalDateTime createdAt;

}
