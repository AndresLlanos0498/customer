package com.customer.app.service.impl;

import com.customer.app.models.ExchangeRequest;
import com.customer.app.models.dao.ExchangeDao;
import com.customer.app.models.documents.Exchange;
import com.customer.app.service.CustomerService;
import com.customer.app.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeDao exchangeDao;

    @Autowired
    private CustomerService customerService;

    @Override
    public Flux<Exchange> findAll() {
        return exchangeDao.findAll();
    }

    @Override
    public Mono<Exchange> findById(String id) {
        return exchangeDao.findById(id);
    }

    @Override
    public Mono<Exchange> create(ExchangeRequest exchangeRequest) {
        // Obtener el valor del campo 'id' de ExchangeRequest
        String customerId = exchangeRequest.getCustomer().getId();

        // Consultar la entidad 'Customer' en la base de datos por su 'id'
        return customerService.findById(customerId)
                .flatMap(customer -> {
                    // Validar si el cliente no está activo utilizando el método isActive
                    if (!customer.isActive()) {
                        throw new RuntimeException("El cliente no está activo"); // O utiliza una excepción personalizada
                    }

                    // Realizar el cálculo de 'finalAmount'
                    double finalAmount = exchangeRequest.getAmount() * exchangeRequest.getExchangeRate();

                    // Obtener la fecha y hora actual
                    LocalDateTime now = LocalDateTime.now();

                    // Crear un nuevo objeto 'Exchange' utilizando los valores calculados y la fecha y hora actual
                    Exchange exchange = new Exchange(
                            null,  // El ID se generará automáticamente por MongoDB
                            customer,
                            exchangeRequest.getAmount(),
                            exchangeRequest.getOriginCurrency(),
                            exchangeRequest.getDestinationCurrency(),
                            exchangeRequest.getExchangeRate(),
                            finalAmount,  // Usar el valor calculado de finalAmount
                            now  // Usar la fecha y hora actual
                    );

                    // Guardar el nuevo objeto 'Exchange' en la base de datos
                    return exchangeDao.save(exchange);
                });
    }

    @Override
    public Mono<Exchange> update(ExchangeRequest exchange) {
        return null;
    }
}
