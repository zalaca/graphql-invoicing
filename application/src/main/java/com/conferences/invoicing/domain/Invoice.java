package com.conferences.invoicing.domain;

import com.conferences.invoicing.commands.CreateInvoiceCommand;
import com.conferences.invoicing.constants.InvoiceStatus;
import com.conferences.invoicing.domain.scalars.Money;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.SiteBoundariesView;
import com.conferences.invoicing.views.WarehouseView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class Invoice {

    private Long id;
    private String invoiceNumber;
    private Customer customer;

    private LocalDate issueDate;
    private LocalDate dueDate;

    private InvoiceStatus status;

    private Set<InvoiceLine> lines;
    private Set<Address> addresses;

    public void addLine(InvoiceLine line) {

        if (status != InvoiceStatus.DRAFT) {

            throw new IllegalStateException();
        }

        if (Objects.isNull(line)) {

            throw new IllegalStateException();
        }

        lines.add(line);
    }

    public BigDecimal getTotal() {

        return Optional.ofNullable(lines).orElse(Set.of())
                .stream()
                .map(InvoiceLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Money getTotalMoney() {

        return Money.builder()
                .amount(this.getTotal())
                .currency(Currency.getAvailableCurrencies().stream().findAny().orElse(null))
                .build();
    }

    public static Invoice createInvoice(CreateInvoiceCommand invoiceCommand) {

        Set<InvoiceLine> lines = invoiceCommand.lines();

        if (Objects.isNull(lines) || lines.isEmpty()) {

            throw new IllegalStateException();
        }

        return Invoice.builder()
                .status(InvoiceStatus.DRAFT)
                .invoiceNumber(invoiceCommand.invoiceNumber())
                .customer(Customer.builder()
                        .id(invoiceCommand.customerId())
                        .build())
                .issueDate(invoiceCommand.issueDate())
                .dueDate(invoiceCommand.dueDate())
                .lines(invoiceCommand.lines())
                .build();
    }
}
