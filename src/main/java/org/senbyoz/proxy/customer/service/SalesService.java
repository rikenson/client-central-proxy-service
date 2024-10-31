package org.senbyoz.proxy.customer.service;

import lombok.RequiredArgsConstructor;
import org.senbyoz.proxy.customer.client.AccountClient;
import org.senbyoz.proxy.customer.client.CustomerClient;
import org.senbyoz.proxy.customer.client.MasterProfileClient;
import org.senbyoz.proxy.customer.domain.CustomerResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final CustomerClient customerClient;
    private final AccountClient accountClient;
    private final MasterProfileClient mcp;

    Flux<CustomerResponse> getSales() {
        var customers = customerClient.getCustomers();
        var accounts = accountClient.getAccounts();
        var parties = mcp.getParties("type");

        if (Objects.nonNull(customers) && Objects.nonNull(accounts) && Objects.nonNull(parties)) {
            return Flux.zip(customers, accounts, parties)
                    .map(tuple -> CustomerResponse.builder()
                            .id(tuple.getT1().id())
                            .individual(tuple.getT3())
                            .build());
        }
        return Flux.empty();
    }

}
