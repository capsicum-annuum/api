package br.com.annuum.capsicum.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
public abstract class User {

    @Id
    @GeneratedValue(generator = "user_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @CreatedDate
    private LocalDateTime createdAt;
}
