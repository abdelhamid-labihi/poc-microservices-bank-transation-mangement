package org.sid.beneficiary_service.repositories;

import org.sid.beneficiary_service.entities.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByNomContainingIgnoreCase(String nom);
    boolean existsByRib(String rib);
}