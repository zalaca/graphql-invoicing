package com.conferences.invoicing.driven.mappers;

import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.driven.models.CustomerMO;
import com.conferences.invoicing.driven.models.InvoiceLineMO;
import com.conferences.invoicing.driven.models.InvoiceMO;
import org.mapstruct.*;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InvoiceMapper {

  InvoiceLineMO map(InvoiceLine line);

  CustomerMO map(Customer customer);

  Customer map(CustomerMO customer);

  InvoiceMO map(Invoice invoice);

  @Mapping(target = "customer", ignore = true)
  @Mapping(target = "lines", ignore = true)
  Invoice map(InvoiceMO invoice);

  Invoice mapFullInvoice(InvoiceMO invoice);

  @AfterMapping
  default void afterMapping(@MappingTarget InvoiceMO invoice) {

    if (invoice.getLines() != null) {
      invoice.getLines().forEach(child -> {
        child.setInvoice(invoice);
      });
    }

    CustomerMO customer = invoice.getCustomer();

    if (Objects.nonNull(customer)) {

      customer.setVatNumber("vat");
      customer.setEmail(UUID.randomUUID().toString());
      customer.setName("name");
    }
  }
}
