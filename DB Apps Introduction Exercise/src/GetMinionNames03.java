import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames03 {
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
                connection.prepareStatement("SELECT `name` FROM villains" +
                        " WHERE id = ?;");

        System.out.println("Enter Villains Id:");
        String nameId = scan.nextLine();
        stmt.setString(1, nameId);

        ResultSet resultSet = stmt.executeQuery();

        if (!resultSet.next()) {
            System.out.printf("No villain with ID %s exists in the database.", nameId);
            return;
        } else {
            System.out.printf("Villain: %s%n", resultSet.getString("name"));
        }

        PreparedStatement preparedStatement2 =
                connection.prepareStatement("SELECT DISTINCT m.`name`, m.age FROM villains AS v" +
                        " JOIN minions_villains AS mv ON mv.villain_id = v.id" +
                        " JOIN minions AS m ON mv.minion_id = m.id" +
                        " WHERE v.id = ?;");
        preparedStatement2.setString(1, nameId);

        ResultSet resultSet1 = preparedStatement2.executeQuery();

        for (int i = 1; resultSet1.next(); i++) {
            System.out.printf("%d. %s %d%n", i, resultSet1.getString("m.name"),
                    resultSet1.getInt("m.age"));
        }

        connection.close();
    }
}
