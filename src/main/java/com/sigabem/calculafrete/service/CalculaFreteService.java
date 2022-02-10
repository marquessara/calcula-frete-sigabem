package com.sigabem.calculafrete.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.sigabem.calculafrete.dto.DadosDTO;
import com.sigabem.calculafrete.dto.RequestDTO;
import com.sigabem.calculafrete.dto.ResponseDTO;
import com.sigabem.calculafrete.model.CalculaFreteLog;
import com.sigabem.calculafrete.repository.CalculaFreteLogRepository;

@Service
public class CalculaFreteService {

	@Autowired
	private CalculaFreteLogRepository repository;
	
    public List<Integer> calcularFrete(RequestDTO requestDTO) {

        boolean isDDDIguais = verificarDDDIguais(requestDTO.getCepOrigem(), requestDTO.getCepDestino());
        boolean isMesmoEstado = verificarMesmoEstado(requestDTO.getCepOrigem(), requestDTO.getCepDestino());

        Integer valorFrete = requestDTO.getPeso().intValue();
        Integer diasParaEntregar = 10;

        if(isDDDIguais){
            valorFrete = valorFrete/2;
            diasParaEntregar = 1;
        }

        if (isMesmoEstado && !isDDDIguais){

            Double valor = valorFrete * 0.75;
            valorFrete = valor.intValue();
            diasParaEntregar = 3;
        }

        return Arrays.asList(valorFrete, diasParaEntregar);
    }

    private boolean verificarDDDIguais(String cepOrigem, String cepDestino) {

        String dddOrigem = verificarDDD(cepOrigem).getDdd();
        String dddDestino = verificarDDD(cepDestino).getDdd();

        return dddOrigem.equals(dddDestino);
    }

    private boolean verificarMesmoEstado(String cepOrigem, String cepDestino) {

        String dddOrigem = verificarDDD(cepOrigem).getUf();
        String dddDestino = verificarDDD(cepDestino).getUf();

        return dddOrigem.equals(dddDestino);
    }

    private DadosDTO verificarDDD(String cep) {

            RestTemplate restTemplate = new RestTemplate();

            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("viacep.com.br")
                    .path("ws/" + cep + "/json/")
                    .build();

            ResponseEntity<DadosDTO> dadosEntity = null;

            try{
                dadosEntity = restTemplate.getForEntity(uri.toUriString(), DadosDTO.class);
            } catch (Exception e){
                System.out.println("Nulo" + dadosEntity.getBody());
            }

            return dadosEntity.getBody();

    }

    @Transactional
   	public void saveCalculaFreteLog(RequestDTO requestDto, ResponseDTO responseDto) {
   		CalculaFreteLog calculaFreteLog = CalculaFreteLog.builder().cepDestino(requestDto.getCepDestino())
   								 .cepOrigem(requestDto.getCepOrigem())
   								 .dataPrevistaEntrega(responseDto.getDataPrevistaEntrega())
   								 .peso(requestDto.getPeso())
   								 .nomeDestinatario(requestDto.getNomeDestinatario())
   								 .vlTotalFrete(responseDto.getVlTotalFrete())
   								 .dataConsulta(LocalDate.now()).build();
   		
   		repository.save(calculaFreteLog);
   								 
   	}
    
    @Transactional
	public List<CalculaFreteLog> buscaFretes() {
		return repository.findAll();
	}
    	
}


