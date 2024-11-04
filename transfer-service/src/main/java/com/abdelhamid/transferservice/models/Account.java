package com.abdelhamid.transferservice.models;

import com.abdelhamid.transferservice.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private Long RIB;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
