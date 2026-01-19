package com.conferences.invoicing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class Warehouse {

    private String id;
    private String name;
    private Set<String> boundaries;
}

