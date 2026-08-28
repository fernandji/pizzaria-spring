package br.com.fernando.pizzaria_spring.database.repository;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findClienteEntityById(Long id);
}
