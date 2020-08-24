package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.EventPeriod;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class MovementRequest implements EventPeriod {

    @NotBlank(message = "O título não pode estar vazia.")
    private String title;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String description;

    @NotNull(message = "A descrição não pode ser nula.")
    private AddressRequest addressRequest;

    @FutureOrPresent
    @NotNull(message = "A data/hora de início não pode ser nula.")
    private LocalDateTime dateTimeStart;

    @Future
    @NotNull(message = "A data/hora de término não pode ser nula.")
    private LocalDateTime dateTimeEnd;

    @Nullable
    private List<PictureRequest> pictureRequests;

    @NotEmpty(message = "A lista de Necessidades não pode estar vazia.")
    private List<NeedRequest> needsRequest;

    @NotEmpty(message = "A lista de Causas não pode estar vazia.")
    private List<Long> causeThatSupport;

}
