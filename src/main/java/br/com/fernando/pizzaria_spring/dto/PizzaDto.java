package br.com.fernando.pizzaria_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaDto {
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private int quantidade;
}
