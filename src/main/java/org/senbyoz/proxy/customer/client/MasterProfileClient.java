package org.senbyoz.proxy.customer.client;

import org.senbyoz.proxy.common.CustomFunctional;
import org.senbyoz.proxy.common.config.ApiUrlConfig;
import org.senbyoz.proxy.customer.domain.Address;
import org.senbyoz.proxy.customer.domain.Party;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class MasterProfileClient {

    private final WebClient webclient;

    public MasterProfileClient(WebClient.Builder webClientBuilder, ApiUrlConfig urlConfig) {
        this.webclient = webClientBuilder.baseUrl(urlConfig.getMcpBaseUrl()).build();
    }

    public Flux<Party> getParties(String type) {
        return parties.apply(webclient, type);
    }

    public Mono<Party> getPartiesByTypeAndId(String customerId, String type) {
        return partiesByTypeAndId.apply(customerId, type, webclient);
    }

    public Flux<Address> getAddressByPartyId(String partyId) {
        return addressesByPartyId.apply(webclient, partyId);
    }

    public Mono<Address> getAddressByIdAndStatus(String partyId, String type) {
        return addressByIdAndStatus.apply(webclient, partyId, type);
    }


    private final BiFunction<WebClient, String, Flux<Party>> parties = (wClient, type) ->
            wClient.get()
                    .uri("?type={type}", type)
                    .retrieve()
                    .bodyToFlux(Party.class)
                    .onErrorResume(e -> Flux.empty());


    private final CustomFunctional.TriFunc<String, String, WebClient, Mono<Party>> partiesByTypeAndId
            = (customerId, type, wClient) -> {
        var uriParams = String.format("?customerId=%s&type=%s", customerId, type);
        return wClient.get()
                .uri("", uriParams)
                .retrieve()
                .bodyToMono(Party.class)
                .onErrorResume(e -> Mono.empty());
    };


    private final BiFunction<WebClient, String, Flux<Address>> addressesByPartyId = (wClient, partyId) ->
            wClient.get()
                    .uri("addresses?partyId={partyId}", partyId)
                    .retrieve()
                    .bodyToFlux(Address.class)
                    .onErrorResume(e -> Flux.empty());


    private final CustomFunctional.TriFunc<WebClient, String, String, Mono<Address>> addressByIdAndStatus
            = (wClient, partyId, status) -> {
        var uriParams = String.format("type=%s&status=%s", partyId, status);
        return wClient.get()
                .uri("addresses?{uriParams}", uriParams)
                .retrieve()
                .bodyToMono(Address.class)
                .onErrorResume(e -> Mono.empty());
    };
}
