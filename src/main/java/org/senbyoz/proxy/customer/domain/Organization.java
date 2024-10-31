package org.senbyoz.proxy.customer.domain;

import java.util.Set;

public record Organization(
        String id,
        String legalName,
        Set<Contact> contacts,
        Set<String> phones,
        Set<String> emails) implements Party {
}
