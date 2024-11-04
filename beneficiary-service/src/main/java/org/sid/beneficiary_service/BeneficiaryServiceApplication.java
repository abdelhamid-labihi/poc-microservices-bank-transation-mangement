package org.sid.beneficiary_service;

import org.sid.beneficiary_service.entities.Beneficiary;
import org.sid.beneficiary_service.entities.BeneficiaryType;
import org.sid.beneficiary_service.repositories.BeneficiaryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeneficiaryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeneficiaryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BeneficiaryRepository beneficiaryRepository) {
		return args -> {
			beneficiaryRepository.save(new Beneficiary(null, "Abdelhamid", "labihi-1", "123456789", BeneficiaryType.PHYSIQUE));
			beneficiaryRepository.save(new Beneficiary(null, "Abdelhamid-1", "labihi-2", "123456789", BeneficiaryType.MORALE));
			beneficiaryRepository.save(new Beneficiary(null, "Abdelhamid-2", "labihi-3", "123456789", BeneficiaryType.PHYSIQUE));
			beneficiaryRepository.save(new Beneficiary(null, "Abdelhamid-3", "labihi-4", "123456789", BeneficiaryType.MORALE));

			beneficiaryRepository.findAll().forEach(beneficiary -> {
				System.out.println(beneficiary.toString());
			});
		};
	}

}
