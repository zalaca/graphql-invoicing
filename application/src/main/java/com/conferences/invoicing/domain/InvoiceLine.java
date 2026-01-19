package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class InvoiceLine {

    private Long id;
    private String description;

    private BigDecimal amount;

    protected InvoiceLine() {}
}

