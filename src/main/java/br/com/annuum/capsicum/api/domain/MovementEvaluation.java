package br.com.annuum.capsicum.api.domain;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "user_organization_evaluation_sequence", sequenceName = "user_organization_evaluation_sequence", allocationSize = 1)
public class MovementEvaluation {

    @Id
    @GeneratedValue(generator = "user_organization_evaluation_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @OneToOne
    private Candidacy candidacy;

    @NotNull
    @Min(value = 1, message = "A nota mínima deve ser 1")
    @Max(value = 5, message = "A nota máxima deve ser 5")
    private Integer rate;

    @Size(max = 200)
    private String feedback;

    @CreatedDate
    private LocalDateTime createdAt;
}
