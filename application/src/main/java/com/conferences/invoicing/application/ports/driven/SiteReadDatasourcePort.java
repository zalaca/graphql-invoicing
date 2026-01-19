package com.conferences.invoicing.application.ports.driven;

import com.conferences.invoicing.domain.Address;
import com.conferences.invoicing.views.SiteBoundariesView;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionStage;

public interface SiteReadDatasourcePort {

  CompletionStage<Map<Long, List<Address>>> getAddressesForInvoices(Set<Long> invoiceIds);

  CompletionStage<Map<String, List<Address>>> getAddressesForCustomers(Set<String> customerIds);
}
