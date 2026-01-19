package com.conferences.invoicing.domain.projections;

public interface InvoiceWithLineProjection {

    Long getInvoiceId();

    InvoiceLineProjection getLine();
}
