package org.senbyoz.proxy.customer.client;

import org.senbyoz.proxy.common.config.ApiUrlConfig;
import org.senbyoz.proxy.customer.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class CustomerClient {

    private final WebClient webclient;

    public CustomerClient(WebClient.Builder webClientBuilder, ApiUrlConfig urlConfig) {
        this.webclient = webClientBuilder.baseUrl(urlConfig.getCustomerBaseUrl()).build();
    }

    public Flux<Customer> getCustomers() {
        return customers.apply(webclient);
    }

    public Mono<Customer> getCustomerById(String customerId) {
        return customersById.apply(customerId, webclient);
    }


    private final Function<WebClient, Flux<Customer>> customers = wClient ->
            wClient.get()
                    .uri("")
                    .retrieve()
                    .bodyToFlux(Customer.class)
                    .onErrorResume(e -> Mono.empty());

    private final BiFunction<String, WebClient, Mono<Customer>> customersById = (customerId, wClient) ->
            wClient.get()
                    .uri("{id}", customerId)
                    .retrieve()
                    .bodyToMono(Customer.class)
                    .onErrorResume(e -> Mono.empty());

}
