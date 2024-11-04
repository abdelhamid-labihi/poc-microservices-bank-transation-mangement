package com.abdelhamid.transferservice.entities;

import com.abdelhamid.transferservice.enums.TransferType;
import com.abdelhamid.transferservice.models.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Entity @Builder
@NoArgsConstructor @AllArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_id_seq")
    private Long id;
    private Long id_source;
    private Long RIB_source;
    private Double montant;
    private String description;
    private Date date_vir;

    @Enumerated(EnumType.STRING)
    private TransferType type;

    @Transient
    private Account account;
}
