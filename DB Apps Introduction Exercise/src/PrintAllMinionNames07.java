import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class PrintAllMinionNames07 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        // TODO: Set user (Default "root") and password (Default "")
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        String dataBase = "minions_db";

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + dataBase, props);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT `name` FROM minions;");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> minionsList = new ArrayList<>();
        while (resultSet.next()) {
            minionsList.add(resultSet.getString(1));
        }

        int start = 0;
        int end = minionsList.size() - 1;

        for (int i = 0; i < minionsList.size(); i++) {
            System.out.println(i % 2 == 0 ? minionsList.get(start++): minionsList.get(end--));
        }

        connection.close();
    }
}
