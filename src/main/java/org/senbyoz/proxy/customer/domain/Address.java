package org.senbyoz.proxy.customer.domain;

public record Address(
        String id,
        String entityId,
        String civicNumber,
        String city,
        String province,
        String postalCode,
        String country) {
}
