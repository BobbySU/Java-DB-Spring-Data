import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure09 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        // TODO: Set user (Default "root") and password (Default "")
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        String dataBase = "minions_db";

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + dataBase, props);

        System.out.println("Enter Minion Id:");
        int minionId = Integer.parseInt(scan.nextLine());

        CallableStatement callableStatement = connection.prepareCall("call usp_get_older (?);");
        callableStatement.setInt(1, minionId);
        callableStatement.executeUpdate();

        connection.close();
    }
}
