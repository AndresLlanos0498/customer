package com.customer.app.service;

import com.customer.app.models.ExchangeRequest;
import com.customer.app.models.documents.Customer;
import com.customer.app.models.documents.Exchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    public Flux<Exchange> findAll();

    public Mono<Exchange> findById(String id);

    public Mono<Exchange> create(ExchangeRequest exchange);

    public Mono<Exchange> update(ExchangeRequest exchange);
}
