# Documentation de l’application de géstion des virements concernant les clients d’une banque.

## Introduction

Cette documentation a pour objectif de fournir un aperçu complet de l'application de gestion des virements d'une banque. L'application est basée sur une architecture microservices utilisant l'écosystème Spring Boot et Spring Cloud pour le backend, et React pour le frontend.

## Table des Matières

1. Conception
2. Architecture de l'Application
3. Microservices
   - Wallet Service
   - Transfer Service
   - Gateway Service
   - Discovery Service
   - Config Service
4. Frontend
5. Sécurité de l'Application
6. Conclusion

## 1. Conception

![](https://github.com/abdelhamid-labihi/abdelhamid-labihi-enset-adria-test-2024/blob/main/assets/conception.png)

## 2. Architecture de l'Applicatio

L'architecture de l'application est basée sur des microservices interagissant via des appels API RESTful. Elle comprend les composants suivants :
- **Beneficiary Service** : Gère les portefeuilles des clients.
- **Transfer Service** : Gère les transferts d'argent entre les portefeuilles.
- **Gateway Service** : Agit comme point d'entrée pour l'application, gère la redirection des demandes aux microservices appropriés.
- **Discovery Service** : Permet aux microservices de s'enregistrer et de découvrir les autres services.
- **Config Service** : Fournit une configuration centralisée pour l'ensemble de l'application.

  ![image](https://github.com/abdelhamid-labihi/abdelhamid-labihi-enset-adria-test-2024/blob/main/assets/architecture.png)

## 2. Microservices

### Les dependances des microservices :
#### - Benificiarie-service :
- spring-boot-starter-actuator
- spring-boot-starter-data-jpa
- spring-boot-starter-data-rest
- spring-boot-starter-web
- spring-cloud-starter-netflix-eureka-client
- spring-boot-devtools
- h2
- lombok
- spring-boot-starter-test
