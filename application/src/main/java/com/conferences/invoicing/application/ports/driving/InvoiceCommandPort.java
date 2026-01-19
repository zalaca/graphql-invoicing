package com.conferences.invoicing.application.ports.driving;

import com.conferences.invoicing.commands.AddInvoiceLineCommand;
import com.conferences.invoicing.commands.CreateInvoiceCommand;
import com.conferences.invoicing.domain.Invoice;

public interface InvoiceCommandPort {

  Invoice handle(CreateInvoiceCommand command);

  void handle(AddInvoiceLineCommand command);
}
