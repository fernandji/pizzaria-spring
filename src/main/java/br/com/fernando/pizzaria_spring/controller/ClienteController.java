package br.com.fernando.pizzaria_spring.controller;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import br.com.fernando.pizzaria_spring.dto.ClienteDto;
import br.com.fernando.pizzaria_spring.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/clientes")
@RequiredArgsConstructor
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteEntity> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ClienteDto> findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCliente(@Valid @RequestBody ClienteDto clienteDto){
        clienteService.addCliente(clienteDto);
    }
}
