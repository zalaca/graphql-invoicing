package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private String id;
    private String name;
    private String email;
    private String vatNumber;

    private Set<Address> addresses;
}

