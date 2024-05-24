
import java.sql.SQLException;
import java.util.Properties;

public class AccountBalanceManagementFunction {

    private KafkaMessageConsumer kafkaConsumer;
    private OracleDatabaseUpdater oracleUpdater;

    public AccountBalanceManagementFunction(Properties kafkaProperties, String kafkaTopic, String jdbcUrl, String dbUsername, String dbPassword) throws SQLException {
        this.kafkaConsumer = new KafkaMessageConsumer(kafkaTopic, kafkaProperties);
        this.oracleUpdater = new OracleDatabaseUpdater(jdbcUrl, dbUsername, dbPassword);
    }

    public void run() {
        while (true) {
            String message = kafkaConsumer.pollMessage();
            if (message != null) {
                // Assume message contains subscriberId and remainingBalance in a comma-separated format
                String[] parts = message.split(",");
                String subscriberId = parts[0];
                double remainingBalance = Double.parseDouble(parts[1]);
                try {
                    oracleUpdater.updateSubscriberBalance(subscriberId, remainingBalance);
                } catch (SQLException e) {
                    e.printStackTrace();  // Handle exception appropriately
                }
            }
        }
    }

    public void close() {
        kafkaConsumer.close();
        try {
            oracleUpdater.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exception appropriately
        }
    }

    public static void main(String[] args) {
        // Load Kafka properties and DB credentials from a configuration file or environment variables
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", "your_kafka_server");
        kafkaProperties.put("group.id", "your_group_id");
        kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        String kafkaTopic = "ABMF";
        String jdbcUrl = "your_jdbc_url";
        String dbUsername = "your_db_username";
        String dbPassword = "your_db_password";

        try {
            AccountBalanceManagementFunction abmf = new AccountBalanceManagementFunction(kafkaProperties, kafkaTopic, jdbcUrl, dbUsername, dbPassword);
            abmf.run();
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exception appropriately
        }
    }
}
