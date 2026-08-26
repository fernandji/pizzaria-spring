package br.com.fernando.pizzaria_spring.service;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import br.com.fernando.pizzaria_spring.database.repository.IClienteRepository;
import br.com.fernando.pizzaria_spring.dto.ClienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository clienteRepository;

    public List<ClienteEntity> findAll(){
        return clienteRepository.findAll();
    }

    public void addCliente(ClienteDto clienteDto) {
        clienteRepository.save(ClienteEntity.builder()
                .nome(clienteDto.getNome())
                .telefone(clienteDto.getTelefone())
                .endereco(clienteDto.getTelefone())
                .build());
    }
}
