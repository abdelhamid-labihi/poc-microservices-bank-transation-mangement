package com.abdelhamid.transferservice;

import com.abdelhamid.transferservice.entities.Transfer;
import com.abdelhamid.transferservice.enums.TransferType;
import com.abdelhamid.transferservice.models.Account;
import com.abdelhamid.transferservice.repositories.TransferRepository;
import com.abdelhamid.transferservice.services.AccountClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class TransferServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransferServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(TransferRepository transferRepository, AccountClient accountClient) {
        return args -> {
            // Retrieve account using the Feign client
            Account account = accountClient.getAccountById(1L);

            if (account != null) { // Check if the account is found
                // Create and save a transfer record
                transferRepository.save(Transfer.builder()
                        .account(account)
                        .date_vir(new Date())
                        .description("Transaction description")
                        .type(Math.random() < 0.5 ? TransferType.INSTANT : TransferType.NORMAL)
                        .montant(145.2)
                        .id_source(account.getId())
                        .RIB_source(account.getRIB()) // assuming ribSource matches the Account RIB attribute
                        .build());
            } else {
                System.out.println("Account not found");
            }
        };
    }

}
