package com.sigabem.calculafrete.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sigabem.calculafrete.dto.RequestDTO;
import com.sigabem.calculafrete.dto.ResponseDTO;
import com.sigabem.calculafrete.model.CalculaFreteLog;
import com.sigabem.calculafrete.resource.CalculaFreteResource;
import com.sigabem.calculafrete.service.CalculaFreteService;

@RestController
public class CalculaFreteController implements CalculaFreteResource {

	
	@Autowired
	private CalculaFreteService service;
	
    @Override	
    public ResponseEntity<ResponseDTO> calculaFrete(@RequestBody RequestDTO requestDTO){


    	List<Integer> listDadosFretePrazo = service.calcularFrete(requestDTO);

        Integer vlTotalFrete = listDadosFretePrazo.get(0);
        Integer prazo = listDadosFretePrazo.get(1);

        LocalDate dataPrevistaEntrega = LocalDate.now().plusDays(prazo);

        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setVlTotalFrete(vlTotalFrete);
        responseDTO.setDataPrevistaEntrega(dataPrevistaEntrega);
        responseDTO.setCepOrigem(requestDTO.getCepOrigem());
        responseDTO.setCepDestino(requestDTO.getCepDestino());
        
        service.saveCalculaFreteLog(requestDTO, responseDTO);

        return ResponseEntity.ok(responseDTO);
    }

	@Override
	public ResponseEntity<List<CalculaFreteLog>> buscaFretes() {
		return ResponseEntity.ok(service.buscaFretes());
	}
}

