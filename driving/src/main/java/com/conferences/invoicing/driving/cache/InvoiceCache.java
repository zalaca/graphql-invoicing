package com.conferences.invoicing.driving.cache;

import com.conferences.invoicing.domain.InvoiceLine;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Component
@RequestScope
public class InvoiceCache {

    private final Map<Long, Set<InvoiceLine>> cache = new HashMap<>();

    public Map<Long, Set<InvoiceLine>> get(Set<Long> ids,
                                           Function<Set<Long>, Map<Long, Set<InvoiceLine>>> loader) {

        if (cache.isEmpty()) {
            cache.putAll(loader.apply(ids));
        }
        return cache;
    }
}

