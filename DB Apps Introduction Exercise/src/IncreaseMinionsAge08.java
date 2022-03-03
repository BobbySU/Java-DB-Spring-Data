import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class IncreaseMinionsAge08 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        // TODO: Set user (Default "root") and password (Default "")
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        String dataBase = "minions_db";

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + dataBase, props);

        System.out.println("Enter Minions Id:");
        String[] minionsId = scan.nextLine().split(" ");

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE minions" +
                " SET age = age + 1," +
                " `name` = LOWER(`name`)" +
                " WHERE id = ?;");

        for (int i = 0; i < minionsId.length; i++) {
            int minionId = Integer.parseInt(minionsId[i]);
            preparedStatement.setInt(1, minionId);
            preparedStatement.executeUpdate();
        }

        PreparedStatement allMinions = connection.prepareStatement(
                "SELECT CONCAT(`name`, \" \", age) AS result FROM minions;");
        ResultSet resultSet = allMinions.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

        connection.close();
    }
}
