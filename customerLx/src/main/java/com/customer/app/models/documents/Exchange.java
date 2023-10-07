package com.customer.app.models.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "exchangev1")
@AllArgsConstructor
@NoArgsConstructor
public class Exchange {
    @Id
    private String id;
    private Customer customer;
    private double amount;
    private String originCurrency;
    private String destinationCurrency;
    private double exchangeRate;
    private double finalAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestDate;


}
