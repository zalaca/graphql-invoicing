package com.conferences.invoicing.domain.projections;

import java.math.BigDecimal;

public interface InvoiceLineProjection {
    Long getId();
    String getDescription();
    BigDecimal getAmount();
}
