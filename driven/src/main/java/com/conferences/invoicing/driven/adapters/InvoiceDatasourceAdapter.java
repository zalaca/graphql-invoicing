package com.conferences.invoicing.driven.adapters;

import com.conferences.invoicing.application.ports.driven.InvoiceDatasourcePort;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.projections.InvoiceWithCustomerProjection;
import com.conferences.invoicing.domain.projections.InvoiceWithLineProjection;
import com.conferences.invoicing.driven.mappers.InvoiceMapper;
import com.conferences.invoicing.driven.models.InvoiceMO;
import com.conferences.invoicing.driven.repositories.InvoiceRepository;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.WarehouseView;
import graphql.schema.DataFetchingEnvironment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InvoiceDatasourceAdapter implements InvoiceDatasourcePort {

  private final InvoiceRepository invoiceRepository;
  private final InvoiceMapper invoiceMapper;

  @Override
  public Window<Invoice> find(Integer first, ScrollPosition scrollPosition) {

    int size = first != null ? first : 20;

    Window<InvoiceMO> window =
            invoiceRepository.findAllBy(
                    scrollPosition,
                    PageRequest.of(0, size)
            );

    return window.map(invoiceMapper::map);
  }

  @Override
  public Optional<Invoice> findById(Long id) {

    return invoiceRepository.findById(id)
            .map(invoiceMapper::map);
  }

  @Override
  public Optional<Invoice> findByIdWithCascade(Long id) {

    return invoiceRepository.findById(id)
            .map(invoiceMapper::mapFullInvoice);
  }

  @Override
  public List<Invoice> findAll() {
    return invoiceRepository.findAll()
            .stream()
            .map(invoiceMapper::map)
            .toList();
  }

  @Override
  public Long save(Invoice invoice) {
    InvoiceMO saved = invoiceRepository.save(invoiceMapper.map(invoice));
    return saved.getId();
  }

  @Override
  public Customer findCustomerByInvoice(Long id) {
    return invoiceRepository.findCustomerByInvoiceId(id)
            .map(invoiceMapper::map)
            .orElse(null);
  }

  @Override
  public Set<InvoiceWithCustomerProjection> getInvoices(Set<Long> ids) {

    return invoiceRepository.findInvoicesWithCustomer(ids);
  }

  @Override
  public Set<InvoiceWithLineProjection> getInvoicesLines(Set<Long> ids) {

    return invoiceRepository.findInvoicesWithLines(ids);
  }

  //@Override
  public Set<InvoiceWithCustomerProjection> getInvoices(DataFetchingEnvironment env)
          throws Exception {

    return (Set<InvoiceWithCustomerProjection>) QuerydslDataFetcher
            .builder(invoiceRepository)
            .projectAs(InvoiceWithCustomerProjection.class)
            .many()
            .get(env);
  }
}
