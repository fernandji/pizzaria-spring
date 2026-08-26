package br.com.fernando.pizzaria_spring.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {
    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotBlank
    private String endereco;
}
