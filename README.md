# springboot-microservices

## 프로젝트 설명

이 프로젝트는 **Spring Boot와 Spring Cloud**를 사용하여 마이크로서비스 아키텍처를 구현하는 방법을 익히기 위해 시작했습니다. 이 README에서는 마이크로 서비스의 주요 내용과 기술들에 대해 설명합니다.

## 📌 주요 내용

- RESTful 웹 서비스 개발 (Spring Boot와 Spring MVC Annotations 사용)
- CRUD REST API 개발 (Spring Boot, Spring Data JPA 및 MySQL 데이터베이스 사용)
- 마이크로서비스 간 통신 (Spring Cloud OpenFeign 사용)
- 서비스 레지스트리 및 디스커버리 구현 (Spring Cloud Netflix Eureka 사용)
- 로드 밸런싱 (Eureka 서버 및 Spring Cloud LoadBalancer 사용)
- API 게이트웨이 생성 (Spring Cloud Gateway 사용)
- 중앙 집중식 설정 관리 (Spring Cloud Config Server 사용)
- 설정 변경 자동 갱신 (Spring Cloud Bus, RabbitMQ 사용)
- 마이크로서비스 간의 장애 전파 방지 (Resilience4J: Circuit Breaker 패턴 사용)
- React 기반 프론트엔드 마이크로서비스 개발 및 백엔드 마이크로서비스와의 통합

## MicroServices

### [service-registry](https://github.com/Jimoou/springboot-microservices/tree/main/service-registry)

서비스 레지스트리는 마이크로서비스들이 서로를 발견할 수 있게 해주는 중앙 집중식 서비스입니다. 이 프로젝트에서는 `Spring Cloud Netflix Eureka`를 사용하여 서비스 레지스트리를 구현합니다.

### [react-frontend](https://github.com/Jimoou/springboot-microservices/tree/main/react-frontend)

React(Typescript) 기반의 프론트엔드 마이크로서비스로, 사용자 인터페이스를 제공하며 백엔드 마이크로서비스와 통신합니다. 각 백엔드 마이크로서비스의 API를 호출하고 응답을 처리하여 화면에 표시합니다.

### [organization-service](https://github.com/Jimoou/springboot-microservices/tree/main/organization-service)

회사 정보를 관리하는 마이크로서비스입니다. RESTful 웹 서비스를 사용하여 조직에 대한 Create, Read 작업을 처리하며, Spring Data JPA와 MySQL 데이터베이스를 사용하여 데이터를 저장합니다.

### [employee-service](https://github.com/Jimoou/springboot-microservices/tree/main/employee-service)

직원 정보를 관리하는 마이크로서비스입니다. RESTful 웹 서비스를 사용하여 직원에 대한 Create, Read 작업을 처리하며, Spring Data JPA와 MySQL 데이터베이스를 사용하여 데이터를 저장합니다.

### [department-service](https://github.com/Jimoou/springboot-microservices/tree/main/department-service)

부서 정보를 관리하는 마이크로서비스입니다. RESTful 웹 서비스를 사용하여 부서에 대한 Create, Read 작업을 처리하며, Spring Data JPA와 MySQL 데이터베이스를 사용하여 데이터를 저장합니다.

### [config-server](https://github.com/Jimoou/springboot-microservices/tree/main/config-server)

중앙 집중식으로 설정 정보를 관리하는 서버입니다. Spring Cloud Config Server를 사용하여 [config-server-repo](https://github.com/Jimoou/config-server-repo) (\*Private)에 있는 모든 마이크로서비스의 설정 정보를 저장하고 제공합니다. 설정 변경이 발생하면 Spring Cloud Bus와 RabbitMQ를 사용하여 모든 마이크로서비스에 변경 사항을 자동으로 전파합니다.

### [api-gateway](https://github.com/Jimoou/springboot-microservices/tree/main/api-gateway)

API 게이트웨이는 클라이언트와 마이크로서비스 간의 통신을 중계하는 역할을 합니다. 이 프로젝트에서는 Spring Cloud Gateway를 사용하여 API 게이트웨이를 구현합니다. 또한, Eureka 서버와 Spring Cloud LoadBalancer를 사용하여 로드 밸런싱을 지원하며, Resilience4J를 사용하여 Circuit Breaker 패턴을 구현하여 마이크로서비스 간의 장애 전파를 방지합니다.

## 작동 과정

### Client 요청

<img width="2706" alt="Untitled (1)" src="https://user-images.githubusercontent.com/109801772/230082167-2ff63540-4f51-4736-b154-ef52d72bdbaa.png">

사용자는 프론트엔드를 통해 요청을 보내며, 프론트엔드는 API 게이트웨이를 통해 해당 요청을 적절한 마이크로서비스로 전달합니다. 각 마이크로서비스는 Eureka 서비스 레지스트리를 통해 다른 서비스를 찾고, 필요한 경우 Config Server로부터 구성 정보를 가져옵니다.  
API 게이트웨이를 통해 전달되는 요청은 Spring Cloud LoadBalancer를 통해 여러 인스턴스 중 하나로 로드 밸런싱됩니다. 이를 통해 시스템 전체의 부하 분산 및 서비스의 가용성을 향상시킬 수 있습니다.

## 기술 설명

### Spring Cloud OpenFeign : 마이크로서비스 간 통신

- **사용 이유**: 마이크로서비스 아키텍처에서 서비스 간 통신이 중요한데, OpenFeign은 선언적 REST 클라이언트를 제공하여 서비스 간 통신을 간단하게 만들어 줍니다.
- **효과**: 개발자가 직접 복잡한 REST 통신 코드를 작성할 필요 없이, 인터페이스 기반의 클라이언트를 사용하여 서비스 간 통신을 쉽게 구현할 수 있습니다.
- **예시**: Employee 서비스에서 Department, Organization 서비스로부터 해당 직원의 부서 정보, 회사 정보를 가져오는 경우, OpenFeign을 사용하면 Department, Organization 서비스에 대한 REST 요청을 추상화할 수 있습니다.

### Spring Cloud Netflix Eureka : 서비스 레지스트리 및 디스커버리 구현

- **사용 이유**: 마이크로서비스 환경에서 서비스 간 통신을 위해 서비스 디스커버리가 필요하며, Eureka는 이를 위한 서비스 레지스트리 솔루션을 제공합니다.
- **효과**: Eureka를 사용하면 각 서비스 인스턴스의 위치를 쉽게 찾을 수 있으며, 서비스 간 통신에 있어 신뢰성과 확장성이 향상됩니다.

### Eureka Server, Spring Cloud LoadBalancer : 로드 밸런싱

- **사용 이유**: 시스템의 부하를 분산시키고 서비스의 가용성을 향상시키기 위해 로드 밸런싱이 필요합니다.
- **효과**: 로드 밸런서를 사용하면 전체 시스템의 부하를 균등하게 분산시킬 수 있으며, 서비스의 가용성과 내구성이 향상됩니다.

### Spring Cloud Gateway : API 게이트웨이 생성

- **사용 이유**: 클라이언트와 마이크로서비스 간 통신을 위한 중앙 집중식 진입점이 필요하며, Spring Cloud Gateway는 이를 위한 솔루션을 제공합니다.
- **효과**: API 게이트웨이를 사용하면 클라이언트는 하나의 진입점으로 모든 마이크로서비스에 접근할 수 있으며, 교차 관심사를 중앙에서 관리할 수 있습니다(예: 인증, 권한 부여, 요청/응답 변환 등).

### Spring Cloud Config Server : 중앙 집중식 설정 관리

- **사용 이유**: 마이크로서비스 환경에서 중앙 집중식으로 구성을 관리하면 유지 보수가 용이해집니다. Spring Cloud Config Server는 이러한 목적으로 사용됩니다.
- **효과**: Config Server를 사용하면 모든 서비스의 구성을 한 곳에서 관리할 수 있으며, 변경 사항이 적용되기 위해 서비스를 재시작할 필요가 없습니다. 이를 통해 유지 보수 및 구성 변경이 쉬워집니다.

### Spring Cloud Bus, RabbitMQ : 설정 변경 자동 갱신

- **사용 이유**: 마이크로서비스 환경에서 구성 변경을 실시간으로 전파하기 위해서 Spring Cloud Bus와 RabbitMQ를 사용함.
- **효과**: Spring Cloud Bus와 RabbitMQ를 사용하면 변경된 구성을 즉시 모든 서비스에 전파할 수 있습니다. 이를 통해 업데이트된 구성을 적용하기 위해 서비스를 재시작할 필요가 없어집니다.

### Resilience4J: Circuit Breaker : 마이크로서비스 간의 장애 전파 방지

- **사용 이유**: 마이크로서비스 간 통신 중 일부 서비스가 실패할 경우 전체 시스템에 장애가 전파되지 않도록 하기 위해 회로 차단기 패턴이 필요합니다. Resilience4J는 이를 구현하는데 사용됩니다.
- **효과**: 회로 차단기 패턴을 사용하면 실패한 서비스로 인한 전체 시스템의 장애 전파를 방지할 수 있습니다. 이를 통해 시스템의 견고성이 향상되며, 장애 발생 시 빠르게 복구할 수 있습니다.
- **예시**: Employee 서비스가 Department 서비스에 의존하는 경우, Department 서비스에 문제가 발생하면 이 문제가 Employee 서비스로 전파될 수 있습니다. 하지만 회로 차단기 패턴을 사용하면, Department 서비스에 일시적인 문제가 발생하더라도 Employee 서비스가 영향을 받지 않도록 보호할 수 있습니다.

## 기술

- Java 17+
- Spring Boot 3.0.5
- Spring Cloud
- Microservices
- React(Typescript)
- Resilience4J
- Gradle
- IntelliJ IDEA
- MySQL
- Postman
- RabbitMQ
