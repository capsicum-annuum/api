package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.annotation.DateStartBeforeDateEnd;
import br.com.annuum.capsicum.api.listener.AttributeEncodeListener;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@EntityListeners(AttributeEncodeListener.class)
@Entity
@SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
@DateStartBeforeDateEnd
public class Movement {

    @Id
    @GeneratedValue(generator = "post_sequence", strategy = GenerationType.SEQUENCE)
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

    private Long PictureId;

    @ManyToMany
    private List<Need> needs;

    @ManyToMany
    private List<Cause> causeThatSupport;

    private String causeMatchCode;

    @CreatedDate
    private LocalDateTime createdAt;

}
