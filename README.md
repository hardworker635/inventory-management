# Inventory Management System API

Inventory Management System API is a backend application that provides RESTful endpoints to perform CRUD operations on product inventory within a warehouse. Built using Spring Boot and integrated with a PostgreSQL database, this service manages product data including stock levels, categories, and thresholds. It can be seamlessly connected to any frontend client responsible for stock tracking and inventory control.

---

## ARCHITECTURE DIAGRAM

---<img width="1250" height="696" alt="untitled (4)" src="https://github.com/user-attachments/assets/51f77c58-7eb1-4486-9a0f-01bed57d4b63" />


## Features

- Create, Modify, Delete and Retrieve Product Entries
- Retrieval of Product Entries via Product Id or SKU (Stock Keeping Unit) given by Warehouse Admin
- Track Stock by increasing or decreasing Stock according to Requirement
- Delete Product Entries via Product Id or SKU
- Modify any of the fields of Product Entry
- Set and Monitor Product Threshold
- Retrieve all Product Entries and Product Entries that have low Stock

---

## Technologies and Dependencies Used

→ Java version 17 (JDK)

→ Spring Boot

→ Intellij IDEA (IDE)

→ Spring Web Dependency 

→ Spring Data JPA

→ Maven

→ PostgreSQL Driver Dependency

→ Spring Boot Starter Validation Dependency (Jakarta Validation)

→ ModelMapper Dependency

---

## Project Structure

```bash
src/
├── main/
│   ├── java/
│   │   └── com/assignment/inventory/inventory_management_system/
│   │       ├── config/                           
│   │       │   └── MapperConfig.java           # Manual Creation of Bean for ModelMapper 
│   │       ├── controller/                              # REST controllers
│   │       │   └── ProductController.java      # Handles product-related endpoints
│   │       ├── dto/                                        # Data Transfer Objects
│   │       │   ├── Category.java                    # Enum for product categories
│   │       │   ├── CreateProductDTO.java     # DTO for fields provided by User for Product 
│   │       │   ├── ProductDTO.java               # DTO for returning product data
│   │       │   └── StockUpdateDTO.java       # DTO for updating stock
│   │       ├── entity/                                     # JPA entities (database models)
│   │       │   └── Product.java                      # Product entity class
│   │       ├── exception/                              # Exception handling
│   │       │   └── GlobalExceptionHandler.java   # Centralized error handler
│   │       ├── repository/                             # Data access layer
│   │       │   └── ProductRepository.java     # Extends JpaRepository for CRUD
│   │       ├── service/                                   # Business logic layer
│   │       │   ├── implementation/               # Service implementations
│   │       │   │   └── ProductService.java      # Implements ProductServiceInterface
│   │       │   └── ProductServiceInterface.java  # Defines all abstract methods for Service layers
│   │       └── InventoryManagementSystemApplication.java  # Main Spring Boot entry point
│   └── resources/
│       ├── static/                               
│       ├── templates/                            
│       └── application.properties             # Application configuration file
├── test/                                         # Unit tests
│   └── (test files)
└── target/                                       # Compiled bytecode and build artifacts

---
```

---

## Getting Started

### **Prerequisites**

- Java 17
- Maven
- PostgreSQL (Optional)

### SetUp

1. Clone the git repository
    
    ```bash
    git clone https://github.com/hardworker635/inventory-management.git
    cd inventory-management
    ```
    

1. The application supports both local and cloud PostgreSQL databases. You can either:
    - Use your **local PostgreSQL instance** by updating credentials in `application.properties`,
    
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```
    
    - Connect to a **hosted PostgreSQL database** on Aiven (Free Tier), which comes pre-configured with the `Product` table.
    
    ```
    spring.datasource.url=jdbc:postgresql://inventorymanagement-konalikhita-87a4.e.aivencloud.com:21062/defaultdb?sslmode=require
    spring.datasource.username=avnadmin
    spring.datasource.password=AVNS_LPyJLE_-5pwTHjGTCCr
    ```
    

1. Build and Run the application using maven commands: 
    
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    

1. The app is available at  **http://localhost:8080/api/products**

---

## API ENDPOINTS

- GET
    - getAllProductEntries() → `/api/products` → Get all products
    - getProductById(productId) - `/api/products/{id}` - Get product by ID
    - getProductBySku(sku) -  `/api/products/sku/{sku}` - Get product by SKU
    - getAllLowStockProductEntries - `/api/products/stock/low` - Get all Products where stock quantity is less than stock threshold
- POST
    - addProductEntry(CreateProductDTO) - `/api/products` - Create a new product
- PUT
    - modifyEntireProductById(productId, CreateProductDTO) - `/api/products/{id}` -  Update a product completely
- PATCH
    - updatePartialProductEntryById(productId, JSON body) - `/api/products/{id}` - Partial update of a product by Id
    - increaseStockQuantityById(productId, StockUpdateDTO) - `/api/products/{id}/stock/increase` - Increase product stock by Id
    - decreaseStockQuantityById(productId, StockUpdateDTO) - ``/api/products/{id}/stock/decrease -` Decrease product stock by Id
- DELETE
    - deleteProductById(productId) - `/api/products/{id}` - Delete product by ID
    - deleteProductBySku(sku) - `/api/products/sku/{sku}` - Delete product by SKU
    

---

## Validation Rules for Product Entry

- SKU: required, 2–60 characters
- Product Name: required
- Product Description: max 400 characters
- Product Price: required, positive
- Product Category: required, must be a valid enum [ELECTRONICS, FASHION, FURNITURE, GROCERIES, HEALTH_AND_BEAUTY, HOME_AND_KITCHEN]
- Stock Quantity: required, zero or positive
- Low Stock Threshold: required, zero or positive

---

## 🗃️ Database Schema

The database schema is auto-generated using JPA/Hibernate based on the entity definitions in the codebase. 

---

## API Collection

The Postman collection can be accessed on https://www.postman.com/spacecraft-pilot-95323467/projects-lk/collection/u1sm2kb/inventorymanagementsystem?action=share&creator=38707295

---

## Author

This project is built by Likhita Konakalla.

Follow on [https://www.linkedin.com/in/likhitakonakalla/](https://www.linkedin.com/in/likhitakonakalla/)
