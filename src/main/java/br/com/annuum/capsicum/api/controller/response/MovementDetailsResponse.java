package br.com.annuum.capsicum.api.controller.response;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class MovementDetailsResponse {

    private Long id;

    private String author;

    private String authorPictureUrl;

    private String title;

    private List<PictureResponse> pictures;

    private String description;

    private String cityName;

    private String federatedUnityAcronym;

    private LocalDateTime start;

    private LocalDateTime end;

    private Integer distance;

    private Boolean available;

    private List<String> causes;

    private List<NeedDetailResponse> needs;

}
