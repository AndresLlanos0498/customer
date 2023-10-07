package com.customer.app.service;

import com.customer.app.models.documents.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * My <b>lombok</b>.
 * My annotations of lombok
 *
 * @Data
 */
public interface CustomerService {

    public Flux<Customer> findAll();

    public Mono<Customer> findById(String id);

    public Mono<Customer> create(Customer customer);

    public Mono<Customer> update(Customer customer);

    public Mono<Boolean> delete(String id);
}
