# Assessment Project

This assessment project includes a basic backend with **REST APIs (CRUD)**, database integration, and third-party HTTP client usage.

## Project Information

- group id : org.akee.prj25
- artifact id : assessment
- version : 0.0.1-SNAPSHOT
- jdk : 21
- spring boot : 3.5.5
- maven
- Spring Data JPA
- SQL Server `mssql-jdbc`
- Retrofit2 -> third-party HTTP client integration
- Postman Mock Server -> third-party HTTP client API
- Lombok

## Features

Base Url : http://localhost:9090

- Customer APIs
     - POST /customer/create
     - GET /customer/detail/{id}
     - PUT /customer/edit/{id}
     - POST /customer/list
     - DELETE /customer/delete/{id}
- Wallet Transaction APIs
     - POST /wallet/transfer
     - POST /wallet/transaction/detail
- Postman Collection
     - https://drive.google.com/file/d/1tzCWY9pD1quWnvWj2cpdDd5vn4xqUd2i/view?usp=sharing
- Database Name
     - TESTDB
