package com.abdelhamid.transferservice.services;

import com.abdelhamid.transferservice.models.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "beneficiary-service", url = "http://localhost:8081")
public interface AccountClient {
    @GetMapping("/beneficiaries/{id}")
    Account getAccountById(@PathVariable("id") Long id);
}
