package org.sid.beneficiary_service.services;

import lombok.RequiredArgsConstructor;
import org.sid.beneficiary_service.entities.Beneficiary;
import org.sid.beneficiary_service.repositories.BeneficiaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    @Override
    public Beneficiary getBeneficiaryById(Long id) throws Exception {
        return beneficiaryRepository.findById(id)
                .orElseThrow(() -> new Exception("Bénéficiaire non trouvé avec l'id: " + id));
    }

    @Override
    public Beneficiary createBeneficiary(Beneficiary beneficiary) {
        if (beneficiaryRepository.existsByRib(beneficiary.getRib())) {
            throw new IllegalArgumentException("Un bénéficiaire avec ce RIB existe déjà");
        }
        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public Beneficiary updateBeneficiary(Long id, Beneficiary beneficiary) throws Exception {
        Beneficiary existingBeneficiary = getBeneficiaryById(id);

        if (!existingBeneficiary.getRib().equals(beneficiary.getRib()) &&
                beneficiaryRepository.existsByRib(beneficiary.getRib())) {
            throw new IllegalArgumentException("Un bénéficiaire avec ce RIB existe déjà");
        }

        existingBeneficiary.setNom(beneficiary.getNom());
        existingBeneficiary.setPrenom(beneficiary.getPrenom());
        existingBeneficiary.setRib(beneficiary.getRib());
        existingBeneficiary.setType(beneficiary.getType());

        return beneficiaryRepository.save(existingBeneficiary);
    }

    @Override
    public void deleteBeneficiary(Long id) throws Exception {
        if (!beneficiaryRepository.existsById(id)) {
            throw new Exception("Bénéficiaire non trouvé avec l'id: " + id);
        }
        beneficiaryRepository.deleteById(id);
    }

    @Override
    public List<Beneficiary> searchBeneficiaries(String nom) {
        return beneficiaryRepository.findByNomContainingIgnoreCase(nom);
    }
}