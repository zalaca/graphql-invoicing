package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Address {

    private String id;
    private Set<BigInteger> boundaries;

    protected Address() {}
}

