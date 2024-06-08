# Kafka to Oracle Database Updater

This Java application is designed to consume messages from Kafka and update an Oracle database. It consists of three main components:

1. **KafkaMessageConsumer**: Consumes messages from a Kafka broker.
2. **OracleDatabaseUpdater**: Updates the Oracle database with the subscriber's balance.
3. **AccountBalanceManagementFunction**: Orchestrates the process of reading messages from Kafka and updating the database.

## Project Structure

- `KafkaMessageConsumer.java`: Contains the Kafka consumer logic.
- `OracleDatabaseUpdater.java`: Contains the database update logic.
- `AccountBalanceManagementFunction.java`: Contains the main functionality to process messages and update the database.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Kafka
- Oracle Database
- Maven (for dependency management)

## Getting Started

### Clone the repository

```sh
git clone https://github.com/yourusername/kafka-oracle-updater.git
cd kafka-oracle-updater
