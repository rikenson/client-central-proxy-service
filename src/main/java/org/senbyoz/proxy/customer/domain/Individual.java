package org.senbyoz.proxy.customer.domain;

import java.time.LocalDate;
import java.util.Set;

public record Individual(
        String id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        Set<String> phones,
        Set<String> emails) implements Party {
}
