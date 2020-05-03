package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.converter.AvailabilityConverter;
import br.com.annuum.capsicum.api.listener.AttributeEncodeListener;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
@EntityListeners(AttributeEncodeListener.class)
@Entity
@SequenceGenerator(name = "need_sequence", sequenceName = "need_sequence", allocationSize = 1)
public class Need {

    @Id
    @GeneratedValue(generator = "need_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne
    private Skill skill;

    private String skillMatchCode;

    @NotNull
    private Integer quantity;

    @NotBlank
    private String description;

    @Convert(converter = AvailabilityConverter.class)
    private Availability availability;

    @ManyToMany
    private List<Application> applications;

    @ManyToMany
    private List<Volunteer> volunteers;

    @NotNull
    private Boolean isActive;

}
