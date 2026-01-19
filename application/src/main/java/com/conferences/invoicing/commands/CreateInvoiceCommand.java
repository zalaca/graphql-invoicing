package com.conferences.invoicing.commands;

import com.conferences.invoicing.domain.InvoiceLine;

import java.time.LocalDate;
import java.util.Set;

public record CreateInvoiceCommand(
        String invoiceNumber,
        String customerId,
        LocalDate issueDate,
        LocalDate dueDate,
        Set<InvoiceLine> lines
) {}

