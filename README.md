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
- spring-boot-starter-actuator
- spring-boot-starter-data-jpa
- spring-boot-starter-data-rest
- spring-boot-starter-web
- spring-cloud-starter-netflix-eureka-client
- spring-boot-devtools
- h2
- lombok
- spring-boot-starter-test


### Service Bénéficiaire

- Objectif : Gérer les bénéficiaires pour les virements bancaires.

- Détails : Chaque bénéficiaire est défini par son identifiant, son nom, son prénom, son RIB (Relevé d'Identité Bancaire) et son type (Physique ou Morale).

- Fonctionnalités : Ce service permet d'ajouter, de mettre à jour et de récupérer les détails des bénéficiaires.

#### Endpoints API
![image](https://github.com/abdelhamid-labihi/abdelhamid-labihi-enset-adria-test-2024/blob/main/assets/beneficiary-1.png)

- `GET /beneficiaries/`
![image](https://github.com/abdelhamid-labihi/abdelhamid-labihi-enset-adria-test-2024/blob/main/assets/beneficiary-2.png)

- `GET /beneficiaries/1` : Récupérer les détails d'un bénéficiaire par ID.
  
![image](https://github.com/abdelhamid-labihi/abdelhamid-labihi-enset-adria-test-2024/blob/main/assets/beneficiary-4.png)

- Actuator
![image](https://github.com/abdelhamid-labihi/abdelhamid-labihi-enset-adria-test-2024/blob/main/assets/beneficiary-3.png)

- `POST /beneficiaries` : Créer un nouveau beneficiaire.
- `PUT /beneficiaries/{id}` : Mettre à jour les informations d'un beneficiaire.
- `DELETE /beneficiaries/{id}` : Supprimer un beneficiaire.

- H2
![image](https://github.com/abdelhamid-labihi/abdelhamid-labihi-enset-adria-test-2024/blob/main/assets/beneficiary-5.png)

### Service Virement
- Objectif : Gérer les virements bancaires.

- Détails : Chaque virement est défini par son identifiant, l'identifiant du bénéficiaire, le RIB source du virement, le montant du virement, la description du virement, la date du virement et son type (Normal ou Instantané).

- Fonctionnalités : Ce service permet de créer, de traiter et de suivre les virements bancaires.

#### Endpoints API
- `GET /transfers`


# Proposition d'une solution de securite 
# Sécurisation des Microservices Bancaires avec Keycloak
### 1.1 Composants principaux
- Keycloak Server (Server d'authentification)
- Service Gateway (Spring Cloud Gateway)
- Microservice Bénéficiaires
- Microservice Virements
- Interface utilisateur (Web/Mobile)

### 1.2 Flux d'authentification
1. L'utilisateur s'authentifie via l'interface utilisateur
2. Keycloak génère un JWT token
3. Le token est utilisé pour toutes les requêtes ultérieures
4. La Gateway vérifie le token pour chaque requête
5. Les microservices valident les permissions

## 2. Architecture de sécurité

### 2.1 Sécurité à plusieurs niveaux
- Niveau Gateway : Authentification générale
- Niveau Microservice : Autorisation spécifique
- Niveau Base de données : Chiffrement des données sensibles

### 2.2 Flux de données sécurisé
```
Client -> HTTPS -> Gateway (JWT Validation) -> Microservices (Permission Check)
```

## 3. Configuration de Keycloak

### 3.1 Installation
```bash
# Utilisation de Docker
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev
```

### 3.2 Configuration du Realm
1. Créer un nouveau realm `banque-realm`
2. Configuration des clients:
```json
{
    "clients": [
        {
            "clientId": "web-banking-app",
            "rootUrl": "http://localhost:4200",
            "redirectUris": ["http://localhost:4200/*"],
            "webOrigins": ["http://localhost:4200"],
            "publicClient": true,
            "protocol": "openid-connect"
        }
    ]
}
```

### 3.3 Configuration des rôles
```json
{
    "roles": {
        "realm": [
            {
                "name": "ROLE_USER",
                "description": "Utilisateur standard"
            },
            {
                "name": "ROLE_ADMIN",
                "description": "Administrateur"
            },
            {
                "name": "ROLE_AGENT",
                "description": "Agent bancaire"
            }
        ]
    }
}
```

## 4. Configuration des Microservices

### 4.1 Dépendances Maven
```xml
<dependencies>
    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- OAuth2 Resource Server -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>
    
    <!-- Keycloak Adapter -->
    <dependency>
        <groupId>org.keycloak</groupId>
        <artifactId>keycloak-spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```

### 4.2 Configuration application.yml
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:8080/realms/banque-realm
          jwk-set-uri: http://localhost:8080/realms/banque-realm/protocol/openid-connect/certs

keycloak:
  realm: banque-realm
  auth-server-url: http://localhost:8080
  ssl-required: external
  resource: web-banking-app
  public-client: true
  principal-attribute: preferred_username
```

### 4.3 Configuration de la sécurité
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/api/beneficiaires/**").hasRole("USER")
                .requestMatchers("/api/virements/**").hasRole("USER")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
```

## 5. Gestion des Rôles et Permissions

### 5.1 Mapping des permissions
| Endpoint | Rôle requis | Description |
|----------|-------------|-------------|
| GET /api/beneficiaires | USER | Liste des bénéficiaires |
| POST /api/beneficiaires | USER | Création bénéficiaire |
| POST /api/virements | USER | Création virement |
| GET /api/admin/reports | ADMIN | Rapports administratifs |

### 5.2 Annotations de sécurité
```java
@RestController
@RequestMapping("/api/virements")
public class VirementController {

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Virement> createVirement(@RequestBody VirementDTO virement) {
        // ...
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reports")
    public ResponseEntity<List<VirementReport>> getReports() {
        // ...
    }
}
```

## 6. Tests et Déploiement

### 6.1 Tests de sécurité
```java
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Test
    @WithMockKeycloakAuth(roles = "USER")
    void testUserAccess() {
        // Tests des endpoints utilisateur
    }

    @Test
    @WithMockKeycloakAuth(roles = "ADMIN")
    void testAdminAccess() {
        // Tests des endpoints admin
    }
}
```

### 6.2 Procédure de déploiement
1. Déployer Keycloak sur l'environnement cible
2. Configurer les URLs dans le realm
3. Mettre à jour les configurations des microservices
4. Déployer les microservices
5. Vérifier les connexions et les tokens

### 6.3 Surveillance et Maintenance
- Monitoring des tokens actifs
- Audit des connexions
- Rotation des clés de signature
- Mise à jour régulière des configurations de sécurité

## Recommandations de sécurité supplémentaires

1. **Chiffrement des données sensibles**
   - Utiliser le chiffrement AES-256 pour les RIB
   - Mettre en place le hachage des données sensibles

2. **Gestion des sessions**
   - Timeout de session : 15 minutes
   - Refresh token : 24 heures
   - Session unique par utilisateur

3. **Logs et Audit**
   - Logger toutes les actions sensibles
   - Implémenter un système d'audit complet
   - Stocker les logs de manière sécurisée
