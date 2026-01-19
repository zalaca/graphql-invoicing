package com.conferences.invoicing.driven.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvoiceLineMO is a Querydsl query type for InvoiceLineMO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInvoiceLineMO extends EntityPathBase<InvoiceLineMO> {

    private static final long serialVersionUID = -1852237631L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvoiceLineMO invoiceLineMO = new QInvoiceLineMO("invoiceLineMO");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInvoiceMO invoice;

    public QInvoiceLineMO(String variable) {
        this(InvoiceLineMO.class, forVariable(variable), INITS);
    }

    public QInvoiceLineMO(Path<? extends InvoiceLineMO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvoiceLineMO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvoiceLineMO(PathMetadata metadata, PathInits inits) {
        this(InvoiceLineMO.class, metadata, inits);
    }

    public QInvoiceLineMO(Class<? extends InvoiceLineMO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.invoice = inits.isInitialized("invoice") ? new QInvoiceMO(forProperty("invoice"), inits.get("invoice")) : null;
    }

}

