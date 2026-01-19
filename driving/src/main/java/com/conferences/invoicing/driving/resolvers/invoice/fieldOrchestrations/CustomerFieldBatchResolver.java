package com.conferences.invoicing.driving.resolvers.invoice.fieldOrchestrations;

import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.application.ports.driving.InvoiceResolverPort;
import com.conferences.invoicing.domain.Address;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.domain.scalars.Money;
import com.conferences.invoicing.driving.cache.InvoiceCache;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CustomerFieldBatchResolver {

    private final SiteReadDatasourcePort siteReadDatasourcePort;

    @BatchMapping(typeName = "Customer", field = "addresses")
    public Mono<Map<Customer, List<Address>>> addresses(List<Customer> customers) {

        Set<String> customerIds = customers.stream()
                .map(Customer::getId)
                .collect(Collectors.toSet());

        CompletionStage<Map<String, List<Address>>> future =
                siteReadDatasourcePort.getAddressesForCustomers(customerIds);

        return Mono.fromCompletionStage(future)
                .map(addressesByCustomerId ->
                        customers.stream()
                                .collect(Collectors.toMap(
                                        customer -> customer,
                                        customer -> addressesByCustomerId
                                                .getOrDefault(customer.getId(), List.of())
                                ))
                );
    }
}
