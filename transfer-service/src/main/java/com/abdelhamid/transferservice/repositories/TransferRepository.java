package com.abdelhamid.transferservice.repositories;

import com.abdelhamid.transferservice.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
