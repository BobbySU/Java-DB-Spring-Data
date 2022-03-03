import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ChangeTownNamesCasing05 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        // TODO: Set user (Default "root") and password (Default "")
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        String dataBase = "minions_db";

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + dataBase, props);

        String countryName = scan.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE towns" +
                " SET `name` = UPPER(`name`)" +
                " WHERE country LIKE ?;");
        preparedStatement.setString(1, countryName);
        int resultCountry = preparedStatement.executeUpdate();

        if (resultCountry == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.printf("%d town names were affected.%n", resultCountry);

        PreparedStatement selectCites = connection.prepareStatement(
                "SELECT `name` FROM towns WHERE country LIKE ?;");
        selectCites.setString(1, countryName);
        ResultSet selectCitesSet = selectCites.executeQuery();

        List<String> cityNames = new ArrayList<>();

        while (selectCitesSet.next()) {
            cityNames.add(selectCitesSet.getString("name"));
        }
        System.out.println(cityNames);

        connection.close();
    }
}
