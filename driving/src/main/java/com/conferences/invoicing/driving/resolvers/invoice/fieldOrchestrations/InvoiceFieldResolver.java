package com.conferences.invoicing.driving.resolvers.invoice.fieldOrchestrations;

import com.conferences.invoicing.application.ports.driving.InvoiceResolverPort;
import com.conferences.invoicing.domain.Address;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class InvoiceFieldResolver {

    private final InvoiceResolverPort invoiceResolverPort;

    // N+1 version (educational example)
    /*@SchemaMapping(typeName = "Invoice", field = "customer")
    public Customer customer(Invoice invoice) {
        return invoiceResolverPort.findCustomerByInvoiceId(invoice.getId());
    }*/
}
