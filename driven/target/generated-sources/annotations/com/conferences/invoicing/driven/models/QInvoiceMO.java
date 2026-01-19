package com.conferences.invoicing.driven.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvoiceMO is a Querydsl query type for InvoiceMO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInvoiceMO extends EntityPathBase<InvoiceMO> {

    private static final long serialVersionUID = 1245538093L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvoiceMO invoiceMO = new QInvoiceMO("invoiceMO");

    public final QCustomerMO customer;

    public final DatePath<java.time.LocalDate> dueDate = createDate("dueDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath invoiceNumber = createString("invoiceNumber");

    public final DatePath<java.time.LocalDate> issueDate = createDate("issueDate", java.time.LocalDate.class);

    public final SetPath<InvoiceLineMO, QInvoiceLineMO> lines = this.<InvoiceLineMO, QInvoiceLineMO>createSet("lines", InvoiceLineMO.class, QInvoiceLineMO.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public QInvoiceMO(String variable) {
        this(InvoiceMO.class, forVariable(variable), INITS);
    }

    public QInvoiceMO(Path<? extends InvoiceMO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvoiceMO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvoiceMO(PathMetadata metadata, PathInits inits) {
        this(InvoiceMO.class, metadata, inits);
    }

    public QInvoiceMO(Class<? extends InvoiceMO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomerMO(forProperty("customer")) : null;
    }

}

