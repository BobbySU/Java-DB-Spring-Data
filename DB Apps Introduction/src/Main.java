import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT user_name, first_name, last_name, COUNT(game_id) AS game FROM users AS u " +
                        "JOIN users_games AS ug ON ug.user_id = u.id " +
                        "WHERE user_name LIKE ? GROUP BY u.id;");

        String username = scan.nextLine();
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.printf("User: %s%n%s %s has played %d games",
                    rs.getString("user_name"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getInt("game"));
        } else {
            System.out.println("No such user exists");
        }

        connection.close();
    }
}
