package com.conferences.invoicing.driven.mappers;

import com.conferences.invoicing.constants.InvoiceStatus;
import com.conferences.invoicing.domain.Address;
import com.conferences.invoicing.domain.Customer;
import com.conferences.invoicing.domain.Invoice;
import com.conferences.invoicing.domain.InvoiceLine;
import com.conferences.invoicing.driven.models.CustomerMO;
import com.conferences.invoicing.driven.models.InvoiceLineMO;
import com.conferences.invoicing.driven.models.InvoiceMO;
import java.time.LocalDate;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-19T16:32:01+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Homebrew)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceLineMO map(InvoiceLine line) {
        if ( line == null ) {
            return null;
        }

        InvoiceLineMO invoiceLineMO = new InvoiceLineMO();

        return invoiceLineMO;
    }

    @Override
    public CustomerMO map(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerMO customerMO = new CustomerMO();

        return customerMO;
    }

    @Override
    public Customer map(CustomerMO customer) {
        if ( customer == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String email = null;
        String vatNumber = null;
        Set<Address> addresses = null;

        Customer customer1 = new Customer( id, name, email, vatNumber, addresses );

        return customer1;
    }

    @Override
    public InvoiceMO map(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceMO invoiceMO = new InvoiceMO();

        afterMapping( invoiceMO );

        return invoiceMO;
    }

    @Override
    public Invoice map(InvoiceMO invoice) {
        if ( invoice == null ) {
            return null;
        }

        Customer customer = null;
        Set<InvoiceLine> lines = null;
        Long id = null;
        String invoiceNumber = null;
        LocalDate issueDate = null;
        LocalDate dueDate = null;
        InvoiceStatus status = null;
        Set<Address> addresses = null;

        Invoice invoice1 = new Invoice( id, invoiceNumber, customer, issueDate, dueDate, status, lines, addresses );

        return invoice1;
    }

    @Override
    public Invoice mapFullInvoice(InvoiceMO invoice) {
        if ( invoice == null ) {
            return null;
        }

        Long id = null;
        String invoiceNumber = null;
        Customer customer = null;
        LocalDate issueDate = null;
        LocalDate dueDate = null;
        InvoiceStatus status = null;
        Set<InvoiceLine> lines = null;
        Set<Address> addresses = null;

        Invoice invoice1 = new Invoice( id, invoiceNumber, customer, issueDate, dueDate, status, lines, addresses );

        return invoice1;
    }
}
