package org.senbyoz.proxy.customer.domain;

public record Account(
        String id,
        String number,
        String balance,
        String currency,
        String status,
        String type,
        String customerId,
        AccountParam param) {
}
