package br.com.fernando.pizzaria_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoRequestDto {

    @NotNull
    private Long clienteId;

    @NotBlank
    private String endereco;

    @NotEmpty
    private List<ItemPedidoRequestDto> itens = new ArrayList<>();

}
