package org.senbyoz.proxy.customer.client;

import org.senbyoz.proxy.common.config.ApiUrlConfig;
import org.senbyoz.proxy.customer.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class AccountClient {

    private final WebClient webclient;

    public AccountClient(WebClient.Builder webClientBuilder, ApiUrlConfig urlConfig) {
        this.webclient = webClientBuilder.baseUrl(urlConfig.getAccountBaseUrl()).build();
    }

    public Flux<Account> getAccounts() {
        return accounts.apply(webclient);
    }

    public Mono<Account> getAccounts(String customerId) {
        return accountByCustomerId.apply(customerId, webclient);
    }

    public final Function<WebClient, Flux<Account>> accounts = wClient ->
            wClient.get()
                    .uri("")
                    .retrieve()
                    .bodyToFlux(Account.class)
                    .onErrorResume(e -> Mono.empty());

    public final BiFunction<String, WebClient, Mono<Account>> accountByCustomerId = (customerId, wClient) ->
            wClient.get()
                    .uri("?customerId={id}", customerId)
                    .retrieve()
                    .bodyToMono(Account.class)
                    .onErrorResume(e -> Mono.empty());

}
