package br.com.fernando.pizzaria_spring.database.repository;

import br.com.fernando.pizzaria_spring.database.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPedidoRepository extends JpaRepository<PedidoEntity, Long> {

    Optional<PedidoEntity> findPedidoEntityById(Long idPedido);
}
