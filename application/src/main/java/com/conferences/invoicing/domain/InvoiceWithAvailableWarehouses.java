package com.conferences.invoicing.domain;

import com.conferences.invoicing.commands.CreateInvoiceCommand;
import com.conferences.invoicing.constants.InvoiceStatus;
import com.conferences.invoicing.views.InvoiceWithAvailableWarehousesView;
import com.conferences.invoicing.views.SiteBoundariesView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class InvoiceWithAvailableWarehouses {

    private Long id;
    private String invoiceNumber;
    private Set<Warehouse> warehouses;

    public static Set<InvoiceWithAvailableWarehouses> extractDetail(Set<InvoiceWithAvailableWarehousesView> invoiceWarehousesView,
                                                                    Set<SiteBoundariesView> siteBoundariesViews) {

        Map<String, Set<String>> boundariesBySites = Optional.ofNullable(siteBoundariesViews)
                .orElse(Set.of())
                .stream()
                .collect(Collectors.toMap(SiteBoundariesView::id, SiteBoundariesView::boundaries));

        return Optional.ofNullable(invoiceWarehousesView)
                .orElse(Set.of())
                .stream()
                .map(inv -> invoiceWarehouseViewToDomain(inv, boundariesBySites))
                .collect(Collectors.toSet());
    }

    private static InvoiceWithAvailableWarehouses invoiceWarehouseViewToDomain(InvoiceWithAvailableWarehousesView invoiceWithWarehousesView,
                                                                               Map<String, Set<String>> boundariesBySites) {

        return InvoiceWithAvailableWarehouses.builder()
                .id(invoiceWithWarehousesView.id())
                .invoiceNumber(invoiceWithWarehousesView.invoiceNumber())
                .warehouses(
                        Optional.ofNullable(invoiceWithWarehousesView.warehouses())
                                .orElse(List.of())
                                .stream()
                                .map(ware ->
                                        Warehouse.builder()
                                                .id(ware.id())
                                                .name(ware.name())
                                                .boundaries(
                                                        boundariesBySites.getOrDefault(ware.id(), Set.of())
                                                )
                                                .build())
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
