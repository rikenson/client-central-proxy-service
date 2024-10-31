package org.senbyoz.proxy.customer.domain;

import java.util.Set;

public record Contact(
        String id,
        String name,
        Set<String> phones,
        Set<String> emails) {
}
