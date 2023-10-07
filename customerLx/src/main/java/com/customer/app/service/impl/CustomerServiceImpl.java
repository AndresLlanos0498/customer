package com.customer.app.service.impl;

import com.customer.app.models.dao.CustomerDao;
import com.customer.app.models.documents.Customer;
import com.customer.app.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * My <b>lombok</b>.
 * My annotations of lombok
 *
 * @Data
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public Flux<Customer> findAll() {
        return dao.findAll();
    }

    @Override
    public Mono<Customer> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<Customer> create(Customer customer) {
        return dao.findById(customer.getId())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        // Cliente con el mismo id ya existe
                        return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Cliente duplicado"));
                    } else {
                        // No existe un cliente con el mismo id, crea uno nuevo
                        customer.setName(customer.getName());
                        customer.setGender(customer.getGender().toUpperCase());
                        customer.setEmail(customer.getEmail());
                        return dao.save(customer);
                    }
                });
    }

    @Override
    public Mono<Customer> update(Customer customer) {
        // Busca el cliente existente por ID
        return dao.findById(customer.getId())
                .flatMap(existingCustomer -> {
                    // Actualiza los campos específicos del cliente existente
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setStatus(customer.getStatus());
                    existingCustomer.setName(customer.getName());
                    // Guarda el cliente actualizado
                    return dao.save(existingCustomer);
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")));
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return dao.findById(id)
                .flatMap(dc -> dao.delete(dc).then(Mono.just(Boolean.TRUE)))
                .defaultIfEmpty(Boolean.FALSE);
    }


}
