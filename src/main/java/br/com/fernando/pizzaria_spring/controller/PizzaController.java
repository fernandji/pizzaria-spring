package br.com.fernando.pizzaria_spring.controller;

import br.com.fernando.pizzaria_spring.database.model.PizzaEntity;
import br.com.fernando.pizzaria_spring.dto.PizzaDto;
import br.com.fernando.pizzaria_spring.service.PizzaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/pizzas")
@RequiredArgsConstructor
@Validated
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PizzaEntity> findAll() {
        return pizzaService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PizzaEntity> getPizzaById(@PathVariable Long id){
        return pizzaService.getPizzaById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPizza(@Valid @RequestBody PizzaDto pizzaDto){
        pizzaService.addPizza(pizzaDto);
    }

}
