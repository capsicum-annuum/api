package br.com.annuum.capsicum.api.controller.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class MovementResponse {

    private Long userAuthorId;

    private String title;

    private String description;

    private LocalDateTime dateTimeStart;

    private LocalDateTime dateTimeEnd;

    private Long PictureId;

}
