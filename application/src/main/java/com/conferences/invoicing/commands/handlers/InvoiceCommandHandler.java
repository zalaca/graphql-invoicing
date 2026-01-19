package com.conferences.invoicing.commands.handlers;

import com.conferences.invoicing.application.ports.driven.InvoiceDatasourcePort;
import com.conferences.invoicing.application.ports.driving.InvoiceCommandPort;
import com.conferences.invoicing.commands.AddInvoiceLineCommand;
import com.conferences.invoicing.commands.CreateInvoiceCommand;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class InvoiceCommandHandler implements InvoiceCommandPort {

    private final InvoiceDatasourcePort invoicePort;

    public Invoice handle(CreateInvoiceCommand command) {

        Invoice toBeCreatedInvoice = Invoice.createInvoice(command);

        Long createdInvoice = invoicePort.save(toBeCreatedInvoice);
        return invoicePort.findByIdWithCascade(createdInvoice).orElseThrow();
    }

    public void handle(AddInvoiceLineCommand command) {

        Invoice invoice = invoicePort.findById(command.invoiceId())
                .orElseThrow(IllegalArgumentException::new);

        InvoiceLine line = InvoiceLine.builder()
                .description(command.description())
                .amount(command.amount())
                .build();

        invoice.addLine(line);
        invoicePort.save(invoice);
    }
}
