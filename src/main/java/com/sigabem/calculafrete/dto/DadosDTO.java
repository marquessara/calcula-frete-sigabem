package com.sigabem.calculafrete.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosDTO {

    private String uf;
    private String ddd;

}

