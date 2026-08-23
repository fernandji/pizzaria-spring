package br.com.fernando.pizzaria_spring.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaDto {
    private String nome;
    private BigDecimal preco;
    private int quantidade;
}
