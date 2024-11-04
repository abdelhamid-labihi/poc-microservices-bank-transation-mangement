package com.abdelhamid.transferservice.services;

import com.abdelhamid.transferservice.repositories.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }
}
