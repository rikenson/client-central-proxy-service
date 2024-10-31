package org.senbyoz.proxy.customer.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private Party individual;
    private CustomerParam param;
    private List<Account> authors;
    private LocalDateTime created;
}
