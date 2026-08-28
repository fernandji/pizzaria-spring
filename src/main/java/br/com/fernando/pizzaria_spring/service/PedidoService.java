package br.com.fernando.pizzaria_spring.service;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import br.com.fernando.pizzaria_spring.database.model.ItemPedidoEntity;
import br.com.fernando.pizzaria_spring.database.model.PedidoEntity;
import br.com.fernando.pizzaria_spring.database.model.PizzaEntity;
import br.com.fernando.pizzaria_spring.database.repository.IClienteRepository;
import br.com.fernando.pizzaria_spring.database.repository.IPedidoRepository;
import br.com.fernando.pizzaria_spring.database.repository.IPizzaRepository;
import br.com.fernando.pizzaria_spring.dto.ClienteDto;
import br.com.fernando.pizzaria_spring.dto.ItemPedidoRequestDto;
import br.com.fernando.pizzaria_spring.dto.PedidoRequestDto;
import br.com.fernando.pizzaria_spring.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final IClienteRepository clienteRepository;
    private final IPedidoRepository pedidoRepository;
    private final IPizzaRepository pizzaRepository;

    public List<PedidoEntity> findAllPedidos(){
        return pedidoRepository.findAll();
    }

    @Transactional
    public void realizarPedido(PedidoRequestDto pedidoRequestDto) {
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
            valorTotalAcumulado.add(subtotalItem);

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

    }
}
