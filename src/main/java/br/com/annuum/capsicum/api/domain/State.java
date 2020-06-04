package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class State {

    @Id
    private Long id;

    private String uf;

    private String name;

    private Double latitude;

    private Double longitude;

}
