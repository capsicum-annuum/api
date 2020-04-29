package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.converter.AvailabilityConverter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
public class Movement {

    @Id
    @GeneratedValue(generator = "post_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne
    private AbstractUser userAuthor;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @OneToOne
    private Address address;

    @NotNull
    private LocalDateTime dateTimeStart;

    @NotNull
    private LocalDateTime dateTimeEnd;

    private Long PictureId;

    @ManyToMany
    private List<Need> needs;

    @NotBlank
    private String skillMatchCode;

    @ManyToOne
    private Cause cause;

    @NotBlank
    private String causeMatchCode;

    @Convert(converter = AvailabilityConverter.class)
    private Availability availability;

    @CreatedDate
    private LocalDateTime createdAt;

}
