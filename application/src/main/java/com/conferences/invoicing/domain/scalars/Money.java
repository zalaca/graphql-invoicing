package com.conferences.invoicing.domain.scalars;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@AllArgsConstructor
public class Money {

    private BigDecimal amount;
    private Currency currency;
}

