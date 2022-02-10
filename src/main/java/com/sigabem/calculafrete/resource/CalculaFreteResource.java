package com.sigabem.calculafrete.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sigabem.calculafrete.dto.RequestDTO;
import com.sigabem.calculafrete.dto.ResponseDTO;
import com.sigabem.calculafrete.model.CalculaFreteLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(path = "/v1/calculafrete")
@Api(value = "/v1/calculafrete", tags = "Operations about frete calculation")
public interface CalculaFreteResource {
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Calculate delivery taxes", response = ResponseDTO.class)
    public ResponseEntity<ResponseDTO> calculaFrete(@RequestBody RequestDTO requestDTO);

	@GetMapping(path = "/busca" ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all deliveries", response = List.class)
    public ResponseEntity<List<CalculaFreteLog>> buscaFretes();
}