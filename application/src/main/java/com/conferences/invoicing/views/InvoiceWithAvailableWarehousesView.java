package com.conferences.invoicing.views;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record InvoiceWithAvailableWarehousesView(
        Long id,
        String invoiceNumber,
        List<WarehouseView> warehouses
) {
    public Set<String> getWarehousesIds() {

        return warehouses.stream()
                .map(WarehouseView::id)
                .collect(Collectors.toSet());
    }
}
