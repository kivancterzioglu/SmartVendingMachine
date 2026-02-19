# Smart Vending Machine

A Java-based vending machine simulation system that manages products, handles transactions, and tracks purchase history. Built with Java 11 and Maven, featuring comprehensive test coverage and mutation testing.

## Features

- **Product Management**: Add, remove, and manage products with stock tracking
- **Transaction Processing**: Handle money insertion, product selection, and change calculation
- **Transaction History**: Track all completed purchases with timestamps
- **Stock Management**: Monitor product availability and inventory levels
- **Input Validation**: Comprehensive error handling for invalid operations
- **Comprehensive Testing**: Full test suite with JUnit 5, code coverage with JaCoCo, and mutation testing with PIT

## Project Structure

```
SmartVendingMachine/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── smartvending/
│   │               ├── VendingMachine.java    # Main vending machine logic
│   │               ├── Product.java            # Product entity
│   │               └── Transaction.java        # Transaction entity
│   └── test/
│       └── java/
│           └── com/
│               └── smartvending/
│                   ├── VendingMachineTest.java
│                   ├── ProductTest.java
│                   ├── TransactionTest.java
│                   └── AllTests.java          # Test suite
├── pom.xml                                     # Maven configuration
└── README.md
```

## Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: 3.6.0 or higher

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/SmartVendingMachine.git
cd SmartVendingMachine/SmartVendingMachine
```

2. Build the project:
```bash
mvn clean install
```

## Usage

### Basic Example

```java
import com.smartvending.*;

// Create a new vending machine
VendingMachine machine = new VendingMachine();

// Add products
Product coke = new Product("Coca Cola", 1.50, 10);
Product pepsi = new Product("Pepsi", 2.00, 5);
machine.addProduct(coke);
machine.addProduct(pepsi);

// Insert money
machine.insertMoney(2.00);

// Select a product
Transaction transaction = machine.selectProduct("Coca Cola");

// Check change
System.out.println("Change: $" + transaction.getChangeGiven());

// View transaction history
List<Transaction> history = machine.getTransactionHistory();
```

### API Overview

#### VendingMachine

- `addProduct(Product product)` - Add a product to the machine
- `insertMoney(double amount)` - Insert money into the machine
- `selectProduct(String productName)` - Purchase a product
- `getChange()` - Get change and reset balance
- `getCurrentBalance()` - Get current balance
- `getAvailableProducts()` - Get list of available products
- `getTransactionHistory()` - Get all completed transactions
- `getTotalInventoryValue()` - Calculate total inventory value

#### Product

- `Product(String name, double price, int stock)` - Create a new product
- `reduceStock()` - Reduce stock by 1
- `restock(int quantity)` - Add stock
- `isAvailable()` - Check if product is in stock
- `setPrice(double price)` - Update product price

#### Transaction

- `Transaction(String productName, double amountPaid, double changeGiven, LocalDateTime date)` - Create transaction
- `getProductName()` - Get product name
- `getAmountPaid()` - Get amount paid
- `getChangeGiven()` - Get change returned
- `getDate()` - Get transaction timestamp
- `toDetailedString()` - Get formatted transaction details

## Testing

The project includes comprehensive test coverage with multiple testing frameworks:

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=VendingMachineTest
```

### Code Coverage (JaCoCo)

Generate code coverage report:

```bash
mvn clean verify
```

View the coverage report:
```
target/site/jacoco/index.html
```

### Mutation Testing (PIT)

Run mutation testing:

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

View mutation test report:
```
target/pit-reports/index.html
```

## Technologies Used

- **Java 11**: Core programming language
- **Maven**: Build automation and dependency management
- **JUnit 5**: Unit testing framework
- **JaCoCo**: Code coverage tool
- **PIT**: Mutation testing framework

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Kıvanç Terzioğlu  https://github.com/kivancterzioglu

## Acknowledgments

- Built as a demonstration of object-oriented programming principles
- Includes comprehensive testing strategies for software quality assurance
