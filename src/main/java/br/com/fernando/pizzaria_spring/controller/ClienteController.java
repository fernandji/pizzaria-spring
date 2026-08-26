package br.com.fernando.pizzaria_spring.controller;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import br.com.fernando.pizzaria_spring.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteEntity> findAll(){
        return clienteService.findAll();
    }
}
