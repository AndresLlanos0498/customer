package com.customer.app.controllers;


import com.customer.app.exceptions.InvalidSuperHeroIdException;
import com.customer.app.models.ExchangeRequest;
import com.customer.app.models.documents.Customer;
import com.customer.app.models.documents.Exchange;
import com.customer.app.service.CustomerService;
import com.customer.app.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/exchange-bs")
public class ExchangeController {
    @Autowired
    ExchangeService service;

    @GetMapping("/list")
    public Flux<Exchange> list() {
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public Mono<Exchange> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Exchange>> create(@Valid @RequestBody ExchangeRequest request) {
        return service.create(request)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer, HttpStatus.CREATED))
                .switchIfEmpty(Mono.error(new InvalidSuperHeroIdException(HttpStatus.NOT_FOUND, "Cliente no encontrado")));

    }
}
