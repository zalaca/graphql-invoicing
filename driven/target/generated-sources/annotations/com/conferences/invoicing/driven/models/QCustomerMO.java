package com.conferences.invoicing.driven.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomerMO is a Querydsl query type for CustomerMO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerMO extends EntityPathBase<CustomerMO> {

    private static final long serialVersionUID = 758909986L;

    public static final QCustomerMO customerMO = new QCustomerMO("customerMO");

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath vatNumber = createString("vatNumber");

    public QCustomerMO(String variable) {
        super(CustomerMO.class, forVariable(variable));
    }

    public QCustomerMO(Path<? extends CustomerMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomerMO(PathMetadata metadata) {
        super(CustomerMO.class, metadata);
    }

}

