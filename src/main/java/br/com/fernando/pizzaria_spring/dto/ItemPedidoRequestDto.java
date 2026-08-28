package br.com.fernando.pizzaria_spring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoRequestDto {

    @NotNull
    private Long pizzaId;

    @NotNull
    private int quantidade;

}
