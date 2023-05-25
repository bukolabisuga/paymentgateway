# Payment Gateway APIs
This doc describes my simple approach to building a payment gateway using a bank simulator.

### Payment Service 
Process a user's card payment via acquiring bank.

**URL** : `/payment/process`

**Method** : `POST`

**Auth required** : None

**Permissions required** : None

**Request body example**

```json
{
  "cardNumber": "1234567893618822",
  "cvv": "222",
  "expiry": "05/28",
  "amount": "200.0",
  "currency": "GBP"
}
```

### Success Response

**Code** : `200 OK`

**Response example**

```json
{
    "message": "Money retrieved successfully!",
    "originalAmount": 200.0,
    "status": "successful",
    "totalAmount": 200.02,
    "currency": "GBP",
    "transactionId": "ca859365-4bcd-4a84-a6c0-ea972fef04f4"
}
```

## Failure Response

**Code** : `200 OK`

**Response examples**

```json
{
  "message": "Failed to retrieve money. Please check your card details or available balance.",
  "originalAmount": 200.0,
  "status": "successful",
  "totalAmount": 200.02,
  "transactionId": "ca859365-4bcd-4a84-a6c0-ea972fef04f4"
}
```

```json
{
    "message": "Invalid payment request.",
    "status": "failed"
}
```

### Payment Transaction Service
Provides details of a previous card transaction.

**URL** : `/payment/{transactionId}`

**Method** : `GET`

**Auth required** : None

**Permissions required** : None

### Success Response

**Code** : `200 OK`

**Response example**

```json
{
  "message": "Money retrieved successfully!",
  "originalAmount": 200.0,
  "status": "successful",
  "totalAmount": 200.02,
  "transactionId": "d14299ad-5051-47c0-ae88-daee7fd2e05e"
}
```

### Failure Response

**Code** : `200 OK`

**Response example**

```json
{
  "timestamp": "2023-05-24T17:13:04.166+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Payment details not found for transaction : e9185820-5fb7-4652-880c-f84d100db0d5",
  "path": "/payment/e9185820-5fb7-4652-880c-f84d100db0d5"
}
```

## Notes

* The `BankSimulator` acts as the acquiring bank. It's a dummy class containing some necessary validations in the process of retrieving funds from a user's card.
* The `PaymentService` handles the payment processing via the acquiring bank simulator when called by the `merchant.
* The `PaymentTransactionService` class handles storage and retrieval of payment transaction data.

## Running the Solution

* Install an IDE for Java/SpringBoot, I used IntelliJ
* Clone the GitHub repo:
* Open the project from your IDE. Using IntelliJ, click `Run` then `Edit Configuration` and select the `PaymentGatewayApplication` under the `Application` tab (you can change the `Unnamed` title).
* Click `apply` then `OK`
* Select the right pointer button beside the application (Unnamed or your chosen name above)
* The application should run at `http://localhost:8888` (you can change the port in `src/main/resources/application.properties`)
* To test the APIs, you can use an API platform like [Postman](https://www.postman.com/) and follow the API docs above.

## Improvements

1. Compliance and Data Protection: I suggest proper card tokenization and hashing algorithms to protect sensitive user data e.g. card pan, pin, cvv, expiry.

2. Distributed Database: Integrating with a distributed database system like SQL, to manage storage and retrieval of transaction data

3. Input Validation: Implement thorough input validation to ensure that the API handles and rejects invalid or malicious input gracefully.

4. Error Handling and Logging: Enhance the error handling mechanism by implementing a centralized exception handling mechanism to handle exceptions and return meaningful error responses to the client.

5. Authentication and Authorization: Implement authentication and authorization mechanisms to secure the APIs. e.g. OAuth 2.0 or JSON Web Tokens (JWT) to authenticate clients and authorize access to protected resources. 

6. Unit and Integration Testing: Expand the test coverage by writing comprehensive unit and integration tests.

7. Performance Optimization: Conduct performance testing and optimization to ensure that the APIs can respond efficiently and handle high load.

8. Security Considerations: Encrypt sensitive data at rest and use secure protocols (e.g., HTTPS).

9. Scalability and High Availability: Improve the software architecture to be scalable e.g. using horizontal scaling by deploying multiple instances of the application behind a load balancer to handle increased traffic.

10. Monitoring and Metrics: Tools like Prometheus, Grafana, or Kibana can collect and visualize relevant metrics and logs, establish alerts and monitoring thresholds.


## Cloud Technologies 

I'd suggest using platforms like `Azure` and `AWS` because they implement concepts like containerization (e.g. Docker) and orchestration frameworks (e.g. Kubernetes) to manage app deployments and high availability.

For example, `Azure` has a simple interface, offers more scalability for virtual machines and offers cost-savings compared to other clouds through discounts on licensing across several regions.
Azure is compatible with Java and .Net-based applications, provides robust security and offers more flexibility for hybrid cloud architecture.

Using cloud technologies like `Azure` also ensure that we adhere to regulations like GDPR and PCI DSS when storing sensitive customer data in payment systems under the required regions (e.g. UK, US, EU, ASIA, etc.)

