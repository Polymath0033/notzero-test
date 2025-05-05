# NotZero Wallet Service 💰

A RESTful API service for managing digital wallets and transactions, built with Spring Boot.

## Features ✨

- 🆕 Wallet creation with zero balance
- 💳 Wallet account management
- 🔄 CREDIT/DEBIT transaction processing
- 📜 Transaction history retrieval
- 🚫 Wallet deactivation
- 📊 Built-in H2 database console
- 📚 Comprehensive API documentation

## Technologies 🛠️

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Flyway** (database migration)
- **Lombok** (boilerplate reduction)
- **SpringDoc OpenAPI 2.8.8** (API documentation)
- **Maven** (build tool)
- **Spring Validation**

## API Documentation

The API is documented using Swagger UI and OpenAPI 3.0:

- Swagger UI: [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
- OpenAPI JSON: [`http://localhost:8080/v3/api-docs`](http://localhost:8080/v3/api-docs)

## Getting Started 🚀

### Prerequisites

- Java 17 JDK
- Maven 3.8+
- Your favorite IDE (IntelliJ IDEA recommended)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/notzero-test.git
2. Navigate to the project directory
   ```bash
   cd notzero-test
3. Build the project by running
   ```bash
   mvn clean install
   ```
   or go to pom.xml and reload the maven icon at the top
### Running the application
```bash
mvn spring-boot:run
```
The application will start on [`http://localhost:8080`](http://localhost:8080)

### Database Access 🔍
The in-memory H2 database console is available at:
[`http://localhost:8080/h2-console`](http://localhost:8080/h2-console)

##### Connection Details:

**JDBC URL:** `jdbc:h2:mem:wallet_db`

**Username:** `polymath`

**Password:** `1234`



### API Endpoints 🌐


#### Wallet Management


**POST** `/api/wallets` - Create new wallet

**GET** `/api/wallets/{walletAccountNumber}` - Get wallet details

**DELETE** `/api/wallets/{walletAccountNumber}` - Disable wallet

#### Transaction Processing



**POST** `/api/wallets/{walletAccountNumber}/transactions` - Process transaction

**GET** `/api/wallets/{walletAccountNumber}/transactions` - Get transaction history


### Response Format 📦


**All responses follow this structure:**

```
{
  "status": 200,
  "message": "Success message",
  "data": {
    // Endpoint-specific data
  }
}

```
