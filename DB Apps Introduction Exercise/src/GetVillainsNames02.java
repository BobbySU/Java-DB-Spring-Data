import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames02 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        // TODO: Set user (Default "root") and password (Default "")
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        String dataBase = "minions_db";

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + dataBase, props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT v.`name`, COUNT(DISTINCT minion_id) AS minions FROM villains AS v" +
                        " JOIN minions_villains AS mv ON mv.villain_id = v.id" +
                        " GROUP BY v.id" +
                        " HAVING minions > ? ORDER BY minions DESC;");

        System.out.print("Enter villain with minions More then > ");
        String having = scan.nextLine();
        stmt.setString(1, having);

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("minions"));
        }

        connection.close();
    }
}
