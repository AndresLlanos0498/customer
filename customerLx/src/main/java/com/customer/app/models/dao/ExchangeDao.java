package com.customer.app.models.dao;

import com.customer.app.models.documents.Exchange;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExchangeDao extends ReactiveMongoRepository<Exchange, String> {
}
