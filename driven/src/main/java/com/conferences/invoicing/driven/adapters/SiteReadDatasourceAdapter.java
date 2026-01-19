package com.conferences.invoicing.driven.adapters;

import com.conferences.invoicing.application.ports.driven.SiteReadDatasourcePort;
import com.conferences.invoicing.domain.Address;
import com.conferences.invoicing.views.SiteBoundariesView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SiteReadDatasourceAdapter implements SiteReadDatasourcePort {

  @Override
  public CompletionStage<Map<Long, List<Address>>> getAddressesForInvoices(Set<Long> invoiceIds) {
    return Mono.delay(Duration.ofMillis(300))
            .map(ignore ->
                    invoiceIds.stream().collect(Collectors.toMap(
                            id -> id,
                            id -> List.of(new Address(
                                    "A-" + id,
                                    Set.of(BigInteger.TEN)
                            ))
                    ))
            ).toFuture();
  }

  @Override
  public CompletionStage<Map<String, List<Address>>> getAddressesForCustomers(Set<String> customerIds) {
    return Mono.delay(Duration.ofMillis(300))
            .map(ignore ->
                    customerIds.stream().collect(Collectors.toMap(
                            id -> id,
                            id -> List.of(new Address(
                                    "A-" + id,
                                    Set.of(BigInteger.ZERO)
                            ))
                    ))
            ).toFuture();
  }
}
