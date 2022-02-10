package com.sigabem.calculafrete.resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sigabem.calculafrete.dto.RequestDTO;
import com.sigabem.calculafrete.dto.ResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(path = "/v1/calculafrete")
@Api(value = "/v1/calculafrete", tags = "Operations about frete calculation")
public interface CalculaFreteResource {
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Calculate frete", response = ResponseDTO.class)
    public ResponseEntity<ResponseDTO> calculaFrete(@RequestBody RequestDTO requestDTO);
	
}


	