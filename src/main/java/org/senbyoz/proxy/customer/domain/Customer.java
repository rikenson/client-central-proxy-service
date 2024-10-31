package org.senbyoz.proxy.customer.domain;

import java.time.LocalDate;
import java.util.List;

public record Customer(
        String id,
        List<String> accountIds,
        String partyId,
        String partyType,
        CustomerParam param,
        LocalDate created) {
}
