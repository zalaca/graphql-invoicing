package com.conferences.invoicing.commands;

import java.math.BigDecimal;
import java.util.Currency;

public record AddInvoiceLineCommand(
        Long invoiceId,
        String description,
        BigDecimal amount
) {}
