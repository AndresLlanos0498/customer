package com.customer.app.models;

import com.customer.app.models.documents.Customer;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {
    @NotNull
    private Customer customer;
    @NotNull
    @Positive
    private double amount;
    @NotBlank
    @NotNull
    private String originCurrency;
    @NotBlank
    @NotNull
    private String destinationCurrency;
    @Positive
    @NotNull
    private double exchangeRate;
}