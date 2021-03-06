package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.domain.enums.PictureRelevance;
import br.com.annuum.capsicum.api.listener.MovementListener;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@EntityListeners(MovementListener.class)
@Entity
@SequenceGenerator(name = "movement_sequence", sequenceName = "movement_sequence", allocationSize = 1)
public class Movement implements EventPeriod, HasPictures {

    @Id
    @GeneratedValue(generator = "movement_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne
    private AbstractUser userAuthor;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    @OneToOne
    private Address address;

    @NotNull
    @FutureOrPresent
    private LocalDateTime dateTimeStart;

    @NotNull
    @Future
    private LocalDateTime dateTimeEnd;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @ManyToMany
    private List<Need> needs;

    @ManyToMany
    private List<Cause> causeThatSupport;

    private String causeMatchCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MovementStatus movementStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public Picture getPictureByRelevance(PictureRelevance pictureRelevance) {
        return pictures.stream()
            .filter(picture -> picture.getPictureRelevance().equals(pictureRelevance))
            .findFirst()
            .orElse(null);
    }

}
