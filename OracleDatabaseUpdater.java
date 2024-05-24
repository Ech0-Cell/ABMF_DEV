
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDatabaseUpdater {

    private Connection connection;

    public OracleDatabaseUpdater(String jdbcUrl, String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

    public void updateSubscriberBalance(String subscriberId, double remainingBalance) throws SQLException {
        String plsql = "{call UPDATE_SUBSCRIBER_BALANCE(?, ?)}";  // Adjust this to match the actual procedure
        try (CallableStatement stmt = connection.prepareCall(plsql)) {
            stmt.setString(1, subscriberId);
            stmt.setDouble(2, remainingBalance);
            stmt.execute();
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
