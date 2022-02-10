package com.sigabem.calculafrete.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ResponseDTO {

    private Integer vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private String cepOrigem;
    private String cepDestino;

}
