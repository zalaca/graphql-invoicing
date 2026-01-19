package com.conferences.invoicing.domain.projections;

public interface InvoiceWithCustomerProjection {

    Long getInvoiceId();

    CustomerProjection getCustomer();
}
