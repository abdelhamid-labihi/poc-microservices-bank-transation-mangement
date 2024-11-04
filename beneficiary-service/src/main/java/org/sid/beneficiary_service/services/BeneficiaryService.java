package org.sid.beneficiary_service.services;

import org.sid.beneficiary_service.entities.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    List<Beneficiary> getAllBeneficiaries();
    Beneficiary getBeneficiaryById(Long id) throws Exception;
    Beneficiary createBeneficiary(Beneficiary beneficiary);
    Beneficiary updateBeneficiary(Long id, Beneficiary beneficiary) throws Exception;
    void deleteBeneficiary(Long id) throws Exception;
    List<Beneficiary> searchBeneficiaries(String nom);
}