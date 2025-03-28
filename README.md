
# Flipkart Daily 

## Project Overview
Flipkart Daily is a system designed to facilitate the browsing of groceries and daily essentials. Users can search for items from the inventory using various filters and sorting criteria, providing them with an overview of available products before placing an order.

## Features
- **Add Items to Inventory**: Define items along with category, brand, and price.
- **Manage Inventory**: Add stock to existing items.
- **Search Items**:
  - Category
  - Brand
  - Price Range
- **Sorting Options**:
  - Lowest price first (default)
  - Highest price first
  - Least quantity first
- **Extensibility**: Designed to support additional filters and sorting options in the future.

---

## API Endpoints

### 1️⃣ Add Items to Inventory
**Endpoint:** `POST /inventory/addItem`

**Request Body (JSON):**
```json
{
  "name": "Milk",
  "quantity": 10,
  "price": 50,
  "category": "Dairy",
  "brand": "Amul",
  "description": "Fresh full cream milk",
  "createdAt": "2025-03-27T12:00:00Z"
}
```
**Response:** `201 Created`

### 2️⃣ Add Inventory Quantities
**Endpoint:** `POST /inventory/addInventory?name={itemName}&quantity={value}`

**Example Requests:**
```
POST /inventory/addInventory?name=Amul Milk&quantity=20
POST /inventory/addInventory?name=Nestle Milk&quantity=15
POST /inventory/addInventory?name=Amul Curd&quantity=5
POST /inventory/addInventory?name=Nestle Curd&quantity=10
POST /inventory/addInventory?name=Britannia Bread&quantity=25
```
**Response:** `200 OK`

### 3️⃣ Search Items by Different Filters
**Endpoint:** `GET /inventory/search`

**Query Parameters:**
- `category` (optional)
- `brand` (optional)
- `priceFrom` (optional)
- `priceTo` (optional)
- `sortBy` (optional, values: `price_asc`, `price_desc`, `quantity_asc`, `quantity_desc`)

**Example Requests & Responses**

**Search by Brand:**
```
GET /inventory/search?brand=Nestle
```
**Response:**
```json
[
  {
    "name": "Nestle Milk",
    "quantity": 15,
    "price": 90,
    "category": "Milk",
    "brand": "Nestle"
  },
  {
    "name": "Nestle Curd",
    "quantity": 10,
    "price": 90,
    "category": "Curd",
    "brand": "Nestle"
  }
]
```

**Search by Category:**
```
GET /inventory/search?category=Milk
```
**Response:**
```json
[
  {
    "name": "Nestle Milk",
    "quantity": 15,
    "price": 90,
    "category": "Milk",
    "brand": "Nestle"
  },
  {
    "name": "Amul Milk",
    "quantity": 20,
    "price": 100,
    "category": "Milk",
    "brand": "Amul"
  }
]
```

**Search by Price Range:**
```
GET /inventory/search?priceFrom=50&priceTo=100
```
**Response:** *(Items between price range 50-100)*

---

## Implementation Details

### Tech Stack:
- Java Spring Boot
- REST API
- In-memory Data Structure (No database used)

### Code Structure:
- **Controller**: Handles API requests
- **Service**: Business logic
- **Model**: Defines data structure
- **Repository**: Manages in-memory data

---

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Sourav-Patel/flipkart-daily
   ```
2. Navigate to the project directory:
   ```bash
   cd flipkart-daily
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. For running `DemoApplication`:
   ```bash
   mvn spring-boot:run "-Dspring-boot.run.profiles=demo"
   ```
5. Access the API via Postman or browser.

---




