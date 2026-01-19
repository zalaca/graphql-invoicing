package com.conferences.invoicing.driving.resolvers.invoice;

import com.conferences.invoicing.application.ports.driving.InvoiceResolverPort;
import com.conferences.invoicing.domain.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Window;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class InvoiceQueryController {

    private final InvoiceResolverPort invoiceResolverPort;

    @QueryMapping
    public List<Invoice> getAllInvoices() {

        return invoiceResolverPort.getAllInvoices();
    }

    @QueryMapping
    public Invoice getInvoiceById(@Argument("id") String id) {

        return invoiceResolverPort.getInvoiceById(Long.valueOf(id));
    }

    @QueryMapping
    public Set<Invoice> getCustomInvoices(@Argument("ids") Set<String> ids) {

        return invoiceResolverPort.getCustomInvoices(ids);
    }

    @QueryMapping
    public Window<Invoice> getInvoices(
            @Argument("first") Integer first,
            @Argument("after") String after
    ) {

        ScrollPosition position;

        if (after == null) {
            position = ScrollPosition.offset();
        } else {
            String decoded = new String(
                    Base64.getDecoder().decode(after),
                    StandardCharsets.UTF_8
            );

            // expected format: "offset:18"
            long offset = Long.parseLong(decoded.split("_")[1]);
            position = ScrollPosition.offset(offset);
        }

        return invoiceResolverPort.getInvoices(first, position);
    }
}
