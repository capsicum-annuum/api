package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class MovementRequest {

    @NotNull(message = "O id do usuário autor não pode ser nulo.")
    private Long userAuthorId;

    @NotBlank(message = "O título não pode estar vazia.")
    private String title;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String description;

    @NotNull(message = "A descrição não pode ser nula.")
    private AddressRequest addressRequest;

    @NotNull(message = "A data/hora de início não pode ser nula.")
    private LocalDateTime dateTimeStart;

    @NotNull(message = "A data/hora de término não pode ser nula.")
    private LocalDateTime dateTimeEnd;

    @Nullable
    private Long PictureId;

    @NotEmpty(message = "A lista de Necessidades não pode estar vazia.")
    private List<NeedRequest> needsRequest;

    @NotEmpty(message = "A lista de Causas não pode estar vazia.")
    private List<Long> causeThatSupport;

}
