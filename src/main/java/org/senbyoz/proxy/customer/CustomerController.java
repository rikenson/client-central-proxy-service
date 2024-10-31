package org.senbyoz.proxy.customer;

import lombok.RequiredArgsConstructor;
import org.senbyoz.proxy.customer.client.AccountClient;
import org.senbyoz.proxy.customer.client.CustomerClient;
import org.senbyoz.proxy.customer.client.MasterProfileClient;
import org.senbyoz.proxy.customer.domain.CustomerResponse;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;


@Controller
@RequiredArgsConstructor
class CustomerController {

    private final AccountClient accountClient;
    private final MasterProfileClient mcpClient;
    private final CustomerClient customerClient;

    @QueryMapping
    public Flux<CustomerResponse> getCustomers() {
        return Flux.empty();
    }
}
