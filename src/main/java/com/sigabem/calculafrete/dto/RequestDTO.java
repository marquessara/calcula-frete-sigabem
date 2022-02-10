package com.sigabem.calculafrete.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RequestDTO {

    private BigDecimal peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;

}