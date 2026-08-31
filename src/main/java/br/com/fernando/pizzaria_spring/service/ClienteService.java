package br.com.fernando.pizzaria_spring.service;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import br.com.fernando.pizzaria_spring.database.repository.IClienteRepository;
import br.com.fernando.pizzaria_spring.dto.ClienteDto;
import br.com.fernando.pizzaria_spring.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository clienteRepository;


    public Optional<ClienteDto> findById(Long idCliente){
        ClienteEntity clienteEntity = clienteRepository.findClienteEntityById(idCliente)
                .orElseThrow(() -> new NotFoundException("Cliente " + idCliente + " não encontrado!"));

        ClienteDto clienteDto = ClienteDto.builder()
                .nome(clienteEntity.getNome())
                .endereco(clienteEntity.getEndereco())
                .telefone(clienteEntity.getTelefone())
                .build();
        return Optional.of(clienteDto);
    }
    public List<ClienteEntity> findAll(){
        return clienteRepository.findAll();
    }

    public void addCliente(ClienteDto clienteDto) {
        clienteRepository.save(ClienteEntity.builder()
                .nome(clienteDto.getNome())
                .telefone(clienteDto.getTelefone())
                .endereco(clienteDto.getEndereco())
                .build());
    }
}
