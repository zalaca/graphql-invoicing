package com.conferences.invoicing.useCases;

import com.conferences.invoicing.application.ports.driven.InvoiceDatasourcePort;
import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.application.ports.driving.InvoiceResolverPort;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.domain.InvoiceWithAvailableWarehouses;
import com.conferences.invoicing.domain.projections.InvoiceWithCustomerProjection;
import com.conferences.invoicing.domain.projections.InvoiceWithLineProjection;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.SiteBoundariesView;
import com.conferences.invoicing.views.WarehouseView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceResolverUseCase implements InvoiceResolverPort {

    private final InvoiceDatasourcePort invoiceDatasourcePort;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDatasourcePort.findAll();
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceDatasourcePort.findById(id).orElse(null);
    }

    @Override
    public Set<Invoice> getCustomInvoices(Set<String> ids) {
        return new HashSet<>(Optional.ofNullable(this.getAllInvoices())
                .orElse(List.of()));
    }

    @Override
    public Window<Invoice> getInvoices(Integer first, ScrollPosition scrollPosition) {
        return invoiceDatasourcePort.find(first, scrollPosition);
    }

    @Override
    public Customer findCustomerByInvoiceId(Long id) {
        return invoiceDatasourcePort.findCustomerByInvoice(id);
    }

    @Override
    public Map<Long, Customer> findCustomersByInvoiceIds(Set<Long> invoiceIds) {
        return invoiceDatasourcePort.getInvoices(invoiceIds)
                .stream()
                .collect(Collectors.toMap(
                        InvoiceWithCustomerProjection::getInvoiceId,
                        p -> new Customer(
                            p.getCustomer().getId(),
                            p.getCustomer().getName(),
                            p.getCustomer().getEmail(),
                            p.getCustomer().getVatNumber(), Set.of())
                ));
    }

    @Override
    public Map<Long, Set<InvoiceLine>> findLinesByInvoices(Set<Long> invoiceIds) {
        return invoiceDatasourcePort.getInvoicesLines(invoiceIds)
                .stream()
                .collect(Collectors.groupingBy(
                        InvoiceWithLineProjection::getInvoiceId,
                        Collectors.mapping(p ->
                                        new InvoiceLine(
                                                p.getLine().getId(),
                                                p.getLine().getDescription(),
                                                p.getLine().getAmount()
                                        ),
                                Collectors.toSet()
                        )
                ));
    }

}
