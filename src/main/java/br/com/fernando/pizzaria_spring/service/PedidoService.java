package br.com.fernando.pizzaria_spring.service;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import br.com.fernando.pizzaria_spring.database.model.ItemPedidoEntity;
import br.com.fernando.pizzaria_spring.database.model.PedidoEntity;
import br.com.fernando.pizzaria_spring.database.model.PizzaEntity;
import br.com.fernando.pizzaria_spring.database.repository.IClienteRepository;
import br.com.fernando.pizzaria_spring.database.repository.IPedidoRepository;
import br.com.fernando.pizzaria_spring.database.repository.IPizzaRepository;
import br.com.fernando.pizzaria_spring.dto.*;
import br.com.fernando.pizzaria_spring.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final IClienteRepository clienteRepository;
    private final IPedidoRepository pedidoRepository;
    private final IPizzaRepository pizzaRepository;

    public Optional<PedidoResponseDto> findPedidoById(Long idPedido){
        PedidoEntity pedidoEntity = pedidoRepository.findPedidoEntityById(idPedido)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));

        PedidoResponseDto pedidoResponseDto = converterParaDto(pedidoEntity);
        return Optional.of(pedidoResponseDto);
    }

    public List<PedidoResponseDto> findAllPedidos() {
        List<PedidoEntity> pedidoEntities = pedidoRepository.findAll();
        List<PedidoResponseDto> pedidoResponseDtos = new ArrayList<>();

        for(PedidoEntity pedidoEntity : pedidoEntities){
            PedidoResponseDto pedidoResponse = converterParaDto(pedidoEntity);
            pedidoResponseDtos.add(pedidoResponse);
        }
        return pedidoResponseDtos;
    }


    public PedidoResponseDto converterParaDto(PedidoEntity pedidoEntity) {

        List<ItemPedidoResponseDto> itensDto = pedidoEntity.getItens().stream()
                .map(item -> ItemPedidoResponseDto.builder()
                        .nomePizza(item.getPizzaEntity().getNome())
                        .quantidade(item.getQuantidade())
                        .valorUnitario(item.getPrecoUnitario())
                        .subtotal(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                        .build())
                .toList();

        return PedidoResponseDto.builder()
                .id(pedidoEntity.getId())
                .nomeCliente(pedidoEntity.getClienteEntity().getNome())
                .endereco(pedidoEntity.getEndereco())
                .dataHora(pedidoEntity.getDataHora())
                .valorTotal(pedidoEntity.getValorTotal())
                .itens(itensDto)
                .build();
    }

    @Transactional
    public PedidoResponseDto realizarPedido(PedidoRequestDto pedidoRequestDto) {
        ClienteEntity clienteEntity = clienteRepository.findClienteEntityById(pedidoRequestDto.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));

        PedidoEntity pedidoEntity = PedidoEntity.builder()
                .clienteEntity(clienteEntity)
                .endereco(pedidoRequestDto.getEndereco())
                .itens(new ArrayList<>())
                .valorTotal(BigDecimal.ZERO)
                .build();


        BigDecimal valorTotalAcumulado = BigDecimal.ZERO;

        for (ItemPedidoRequestDto item : pedidoRequestDto.getItens()) {
            PizzaEntity pizzaEntity = pizzaRepository.findById(item.getPizzaId())
                    .orElseThrow(() -> new NotFoundException("Pizza " + item.getPizzaId() + "não encontrada!"));

            pizzaEntity.setQuantidade(pizzaEntity.getQuantidade() - item.getQuantidade());

            BigDecimal subtotalItem = pizzaEntity.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            valorTotalAcumulado = valorTotalAcumulado.add(subtotalItem);

            ItemPedidoEntity itemPedidoEntity = ItemPedidoEntity.builder()
                    .pizzaEntity(pizzaEntity)
                    .pedidoEntity(pedidoEntity)
                    .quantidade(item.getQuantidade())
                    .precoUnitario(pizzaEntity.getPreco())
                    .build();

            pedidoEntity.getItens().add(itemPedidoEntity);
        }

        pedidoEntity.setValorTotal(valorTotalAcumulado);

        pedidoRepository.save(pedidoEntity);

        return converterParaDto(pedidoEntity);
    }
}
