package br.com.fernando.pizzaria_spring.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoResponseDto {

    private String nomePizza;
    private int quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal subtotal;
}
