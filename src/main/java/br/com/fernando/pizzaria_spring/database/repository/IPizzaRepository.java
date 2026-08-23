package br.com.fernando.pizzaria_spring.database.repository;

import br.com.fernando.pizzaria_spring.database.model.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPizzaRepository extends JpaRepository<PizzaEntity, Long> {

}
