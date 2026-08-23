package br.com.fernando.pizzaria_spring.controller;

import br.com.fernando.pizzaria_spring.database.model.PizzaEntity;
import br.com.fernando.pizzaria_spring.dto.PizzaDto;
import br.com.fernando.pizzaria_spring.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pizzas")
@RequiredArgsConstructor

public class PizzaController {

    private final PizzaService pizzaService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PizzaEntity> findAll() {
        return pizzaService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPizza(@RequestBody PizzaDto pizzaDto){
        pizzaService.addPizza(pizzaDto);
    }
}
