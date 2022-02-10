package com.sigabem.calculafrete.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calcula_frete_log")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculaFreteLog {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "peso")
	private BigDecimal peso;
	
	@Column(name = "cep_origem")
	private String cepOrigem;
	
	@Column(name = "cep_destino")
	private String cepDestino;
	
	@Column(name = "nome_destinatario")
	private String nomeDestinatario;
	
	@Column(name = "vl_total_frete")
	private Integer vlTotalFrete;
	
	@Column(name = "data_prevista_entrega")
	private LocalDate dataPrevistaEntrega;
	
	@Column(name = "data_consulta")
	private LocalDate dataConsulta;

}
