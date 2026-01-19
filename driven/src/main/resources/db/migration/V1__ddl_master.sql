CREATE TABLE IF NOT EXISTS customers (
                                         id           VARCHAR(36) PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL UNIQUE,
    vat_number   VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS invoices (
                                        id BIGSERIAL PRIMARY KEY,
                                        invoice_number  VARCHAR(100) NOT NULL,
    customer_id     VARCHAR(36) NOT NULL,
    issue_date      DATE,
    due_date        DATE,
    status          VARCHAR(20) NOT NULL,

    CONSTRAINT fk_invoice_customer
    FOREIGN KEY (customer_id)
    REFERENCES customers(id)
    );

CREATE TABLE IF NOT EXISTS invoice_lines (
                                             id BIGSERIAL PRIMARY KEY,
                                             invoice_id    BIGSERIAL NOT NULL,
                                             description   VARCHAR(255),
    amount        NUMERIC(15, 2) NOT NULL,

    CONSTRAINT fk_line_invoice
    FOREIGN KEY (invoice_id)
    REFERENCES invoices(id)
    ON DELETE CASCADE
    );
