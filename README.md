# SME E-commerce App (Case Study: Jiji)

An enterprise-grade Spring Boot e-commerce backend application inspired by Jiji, designed for Small and Medium-sized Enterprises (SMEs). This app provides a secure, scalable backend API for sellers and buyers to interact, manage products, handle orders, and support multi-role access.

## 📦 Tech Stack

- **Java 21**
- **Spring Boot 3**
- **Spring Data MongoDB**
- **JWT Authentication**
- **Maven**
- **Lombok**
- **Postman** (for API testing)

## 🧩 Features

### User Management
- User Registration (Buyer, Seller)
- JWT-based Authentication & Authorization
- Role-based Access Control

### Product Management
- Seller Product Creation (with image upload)
- Product Update & Deletion
- Search and Filter Products
- View Product Details

### Order & Transaction
- Place Orders
- View Order History
- Buyer-Seller Communication (coming soon)

### Admin Dashboard (Planned)
- Monitor Users
- Review Products & Transactions

## 📁 Project Structure


## 🧪 Testing the API

1. Import the Postman collection (`postman_collection.json`) into your Postman.
2. Use the following base URL:


3. Example endpoints:
   - `POST /auth/register` – Register user
   - `POST /auth/login` – Login and get JWT token
   - `POST /products` – Create product (SELLER only)
   - `GET /products` – View all products
   - `POST /orders` – Place order (BUYER only)

📚 Case Study: Jiji
This project is modeled after Jiji’s marketplace:

Multiple Sellers & Products

Chat functionality (planned)

Product Search & Filters

Seamless Buyer-Seller Transactions

👨‍💻 Contributors
Idowu Moses Babatunde @mosesidowu5050
Oderinde Suliha @oderindesuliha
Eric Alli-balogun @Ericallibalogun

Let us know if you want this split into frontend/backend repos or want a separate `CONTRIBUTING.md`, API docs in Swagger/OpenAPI format, or deployment info (e.g. Docker, Render, Heroku).


📄 License
This project is licensed under Semicolon Africa.

---


