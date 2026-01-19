package com.conferences.invoicing.application.ports.driving;

import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.domain.InvoiceWithAvailableWarehouses;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InvoiceResolverPort {

  List<Invoice> getAllInvoices();

  Invoice getInvoiceById(Long id);

  Set<Invoice> getCustomInvoices(Set<String> ids);

  Window<Invoice> getInvoices(Integer first, ScrollPosition scrollPosition);

  // Invoice fields resolvers
  Customer findCustomerByInvoiceId(Long id);

  Map<Long, Customer> findCustomersByInvoiceIds(Set<Long> invoiceIds);

  Map<Long, Set<InvoiceLine>> findLinesByInvoices(Set<Long> invoiceIds);
}
