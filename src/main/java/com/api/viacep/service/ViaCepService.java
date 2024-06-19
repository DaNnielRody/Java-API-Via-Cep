package com.api.viacep.service;

import com.api.viacep.dto.ViaCepDto;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {


    public String consultaCampo(ViaCepDto viaCepDto, String campo) {
        return switch (campo) {
            case "cep" -> viaCepDto.cep();
            case "logradouro" -> viaCepDto.logradouro();
            case "complemento" -> viaCepDto.complemento();
            case "bairro" -> viaCepDto.bairro();
            case "localidade" -> viaCepDto.localidade();
            case "uf" -> viaCepDto.uf();
            case "ibge" -> viaCepDto.ibge();
            case "gia" -> viaCepDto.gia();
            case "ddd" -> viaCepDto.ddd();
            case "siafi" -> viaCepDto.siafi();
            default -> null;
        };
    }

}
