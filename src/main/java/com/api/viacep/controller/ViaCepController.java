package com.api.viacep.controller;

import com.api.viacep.dto.ViaCepDto;
import com.api.viacep.service.ViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/via-cep")
public class ViaCepController {

    private final ViaCepService service;

    public ViaCepController(ViaCepService service) {
        this.service = service;
    }

    @GetMapping("{cep}")
    public ResponseEntity<ViaCepDto> consultaViaCep(@PathVariable String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ViaCepDto> response =  restTemplate.getForEntity(
                // getForEntity -> converte a resposta json da api que informamos na uri, no objeto java da classe que colocarmos
                String.format("https://viacep.com.br/ws/%s/json/", cep),
                ViaCepDto.class);
        // E aqui faz o retorno
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/{cep}/{campo}")
    public ResponseEntity<String> consultaCampoViaCep(@PathVariable String cep, @PathVariable String campo){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ViaCepDto> response =  restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json/", cep),
                ViaCepDto.class
        );
        ViaCepDto campoCep = response.getBody();

        // Para evitar que não coloquemos ou que peçamos o retorno de um campo a mais em relação aos campos da API
        if(campo == null){
            return ResponseEntity.notFound().build();
        }

        return campoCep != null
                ? ResponseEntity.ok(service.consultaCampo(campoCep, campo))
                : ResponseEntity.badRequest().build();
    }
}
