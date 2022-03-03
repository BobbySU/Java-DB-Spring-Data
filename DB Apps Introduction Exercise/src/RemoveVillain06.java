import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RemoveVillain06 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        // TODO: Set user (Default "root") and password (Default "")
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        String dataBase = "minions_db";

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + dataBase, props);

        System.out.println("Enter Villains Id:");
        int villainId = Integer.parseInt(scan.nextLine());

        PreparedStatement selectVillain = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?");
        selectVillain.setInt(1, villainId);
        ResultSet villainSet = selectVillain.executeQuery();

        if (!villainSet.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = villainSet.getString("name");

        PreparedStatement selectAllVillainMinions = connection.prepareStatement(
                "SELECT COUNT(minion_id) as m_count" +
                        " FROM minions_villains WHERE villain_id = ?");
        selectAllVillainMinions.setInt(1, villainId);
        ResultSet minionsCountSet = selectAllVillainMinions.executeQuery();
        minionsCountSet.next();

        int countMinionsDeleted = minionsCountSet.getInt("m_count");

        connection.setAutoCommit(false);

        try {
            PreparedStatement deleteMinionsVillains = connection.prepareStatement(
                    "DELETE FROM minions_villains WHERE villain_id = ?");
            deleteMinionsVillains.setInt(1, villainId);
            deleteMinionsVillains.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement(
                    "DELETE FROM villains WHERE id = ?");
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();

            connection.commit();

            System.out.println(villainName + " was deleted");
            System.out.println(countMinionsDeleted + " minions released");

        } catch (SQLException e) {
            e.printStackTrace();

            connection.rollback();
        }

        connection.close();
    }
}
