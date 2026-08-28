package br.com.fernando.pizzaria_spring.service;

import br.com.fernando.pizzaria_spring.database.repository.IClienteRepository;
import br.com.fernando.pizzaria_spring.database.repository.IPedidoRepository;
import br.com.fernando.pizzaria_spring.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final IClienteRepository clienteRepository;
    private final IPedidoRepository pedidoRepository;

    public void realizarPedido(Long idCliente){
        clienteRepository.findClienteEntityById(idCliente)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));
    }
}
