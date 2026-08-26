package br.com.fernando.pizzaria_spring.database.repository;

import br.com.fernando.pizzaria_spring.database.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
