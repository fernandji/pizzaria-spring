package br.com.fernando.pizzaria_spring.service;

import br.com.fernando.pizzaria_spring.database.model.PizzaEntity;
import br.com.fernando.pizzaria_spring.database.repository.IPizzaRepository;
import br.com.fernando.pizzaria_spring.dto.PizzaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final IPizzaRepository pizzaRepository;

    public List<PizzaEntity> findAll() {
        return pizzaRepository.findAll();
    }

    public Optional<PizzaEntity> getPizzaById(Long id){
        return pizzaRepository.findById(id);
    }

    public void addPizza(PizzaDto pizzaDto) {
        pizzaRepository.save(PizzaEntity.builder()
                .nome(pizzaDto.getNome())
                .preco(pizzaDto.getPreco())
                .quantidade(pizzaDto.getQuantidade())
                .build());
    }
}
