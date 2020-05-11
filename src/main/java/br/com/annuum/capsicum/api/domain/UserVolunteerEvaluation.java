package br.com.annuum.capsicum.api.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "user_volunteer_evaluation_sequence", sequenceName = "user_volunteer_evaluation_sequence", allocationSize = 1)
public class UserVolunteerEvaluation {

    @Id
    @GeneratedValue(generator = "user_volunteer_evaluation_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne
    private AbstractUser userEvaluator;

    @NotNull
    @ManyToOne
    private UserVolunteer userVolunteerEvaluated;

    @NotNull
    @ManyToOne
    private Movement movement;

    @NotNull
    @Min(value = 1, message = "A nota mínima deve ser 1")
    @Max(value = 5, message = "A nota máxima deve ser 5")
    private Integer note;

    @Size(max = 200)
    private String feedback;

    @CreatedDate
    private LocalDateTime createdAt;
}
