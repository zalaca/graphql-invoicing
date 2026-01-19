package com.conferences.invoicing.application.ports.driven;

import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.domain.projections.InvoiceWithCustomerProjection;
import com.conferences.invoicing.domain.projections.InvoiceWithLineProjection;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface InvoiceDatasourcePort {

  Window<Invoice> find(Integer first, ScrollPosition scrollPosition);

  Long save(Invoice invoice);

  Optional<Invoice> findByIdWithCascade(Long id);

  Optional<Invoice> findById(Long id);

  List<Invoice> findAll();

  Customer findCustomerByInvoice(Long id);

  Set<InvoiceWithCustomerProjection> getInvoices(Set<Long> ids);

  Set<InvoiceWithLineProjection> getInvoicesLines(Set<Long> ids);
}
