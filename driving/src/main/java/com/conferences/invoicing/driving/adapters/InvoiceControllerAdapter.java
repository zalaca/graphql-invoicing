package com.conferences.invoicing.driving.adapters;

import com.conferences.invoicing.application.ports.driving.InvoiceCommandPort;
import com.conferences.invoicing.commands.AddInvoiceLineCommand;
import com.conferences.invoicing.commands.CreateInvoiceCommand;
import com.conferences.invoicing.domain.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceControllerAdapter {

    private final InvoiceCommandPort invoiceCommandPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody CreateInvoiceCommand request) {

      CreateInvoiceCommand command = new CreateInvoiceCommand(
              request.invoiceNumber(),
              request.customerId(),
              request.issueDate(),
              request.dueDate(),
              request.lines()
      );

      return invoiceCommandPort.handle(command);
    }

    @PostMapping("/{invoiceId}/lines")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addInvoiceLine(
            @PathVariable String invoiceId,
            @RequestBody AddInvoiceLineCommand request
    ) {
      AddInvoiceLineCommand command = new AddInvoiceLineCommand(
              Long.valueOf(invoiceId),
              request.description(),
              request.amount()
      );

      invoiceCommandPort.handle(command);
    }
}
