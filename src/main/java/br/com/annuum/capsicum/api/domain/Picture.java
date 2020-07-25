package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.PictureType;
import br.com.annuum.capsicum.api.listener.NeedListener;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
@EntityListeners(NeedListener.class)
@Entity
@SequenceGenerator(name = "picture_sequence", sequenceName = "picture_sequence", allocationSize = 1)
public class Picture {

    @Id
    @GeneratedValue(generator = "picture_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    private String profilePictureUrl;

    @NotEmpty
    private String profilePictureKey;

    @Enumerated(EnumType.STRING)
    private PictureType pictureType;
}
