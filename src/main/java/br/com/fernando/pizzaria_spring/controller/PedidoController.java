package br.com.fernando.pizzaria_spring.controller;

import br.com.fernando.pizzaria_spring.dto.PedidoRequestDto;
import br.com.fernando.pizzaria_spring.dto.PedidoResponseDto;
import br.com.fernando.pizzaria_spring.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
@RequiredArgsConstructor
@Validated
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponseDto> pedidoResponseDtos(){
        return pedidoService.findAllPedidos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDto pedidoResponseDto(@RequestBody PedidoRequestDto pedido){
        return pedidoService.realizarPedido(pedido);
    }
}
