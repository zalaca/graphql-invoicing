package com.conferences.invoicing.domain.projections;

public interface CustomerProjection {
    String getId();
    String getName();
    String getEmail();
    String getVatNumber();
}
