## Know Your Customer technical test

### Overview
Within the banking industry we need to validate customer details against source systems to ensure a
customer is who they say they are. For administrators we would require lookups to validate
customer details. We might also need to validate that front-end input matches back-end business
logic.
Using Spring Boot and a database technology of your choice, create a new RESTful API with end
points to carry out the following functions:

• Add a new account with a customer

• Lookup a customer/s using an accountId

• Lookup an account/s using a customerId

• Match customer and account details to an input model. 




### Solution services

The current solution has been developed as micro services built using Spring Boot running on an embedded Tomcat web container.

[bank-api](https://localhost:8443/v1/api/)

### Technologies being used

- Spring Boot
- Rest Service
- Spring Security with JWT
- JPA
- PostgreSQL
- Maven
- Docker

### Rest endpoints 
1) Login:
https://localhost:5443/v1/api/auth/signin

2) Add new customer with an account:
https://localhost:5443/v1/api/auth/signup

3) Lookup a customer/s using an accountId:
https://localhost:5443/v1/api/customers/accounts/{accountId}

4) Lookup an account/s using a customerId:
https://localhost:5443/v1/api/customers/{customerId}/accounts

5) Match customer and account details to an input model:
https://localhost:5443/v1/api/customers/{customerId}/accounts/{accountId}/validate



### Build and deployment:
Step 1: build the bank-api project using "mvn clean install"

Step 2: build an image from Dockerfile using command "docker build ./ -t bank-api"

Step 3: Once the image is built successfully run command "docker-compose up" 

Step 4: Create a database in PostgreSQL with name "bankdb"



### Execution
Once the services are up will need to execute /signup rest endpoint which will register a customer with an account.

Once this is done you can use /signin enpoint using username and password which will give you jwt token as a response.

Using this token you can make calls to other rest end points mentioned above.
You will have to add below header attribute in each request 
Authorization = Bearer {jwttoken}




####Sample endpoints, request and response
You can find "/artifacts/bank-api.postman_collection.json" file inside project root folder which you can import into postman and test the endpoints.
Also you can fnd sample request and response in "/artifacts/sample-request-response.txt" file.


