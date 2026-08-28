package br.com.fernando.pizzaria_spring.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDto {

    private Long id;
    private String endereco;
    private BigDecimal valorTotal;
    private LocalDateTime dataHora;

    @Builder.Default
    private List<ItemPedidoResponseDto> itens = new ArrayList<>();

}
