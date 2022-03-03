import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class AddMinion04 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        // TODO: Set user (Default "root") and password (Default "")
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        String dataBase = "minions_db";

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/" + dataBase, props);

        String[] minion = scan.nextLine().split(" ");
        String minionName = minion[1];
        int minionAge = Integer.parseInt(minion[2]);
        String minionTown = minion[3];
        String villainName = scan.nextLine().split(" ")[1];

        int townId = setTownIfNotExist(connection, minionTown);
        int villainId = setVillainIfNotExist(connection, villainName);
        addMinion(connection, minionName, minionAge, townId, villainName);
        int minionId = getLastMinion(connection, minionName);

        setMinions_villains(connection, villainId, minionId);

        connection.close();
    }

    private static void setMinions_villains(Connection connection, int villainId, int minionId) throws SQLException {
        PreparedStatement setMinions_villains = connection.prepareStatement(
                "INSERT INTO minions_villains (minion_id, villain_id) VALUE (?, ?);");
        setMinions_villains.setInt(1, minionId);
        setMinions_villains.setInt(2, villainId);
        setMinions_villains.executeUpdate();
    }

    private static int getLastMinion(Connection connection, String minionName) throws SQLException {
        PreparedStatement getLastMinionId = connection.prepareStatement("SELECT id FROM minions" +
                " WHERE `name` LIKE ?" +
                " ORDER BY id DESC" +
                " LIMIT 1;");
        getLastMinionId.setString(1, minionName);
        ResultSet lastMinionId = getLastMinionId.executeQuery();
        lastMinionId.next();
        return lastMinionId.getInt("id");
    }

    private static void addMinion(Connection connection, String minionName, int minionAge, int townId, String villainName)
            throws SQLException {
        PreparedStatement addMinion = connection.prepareStatement(
                "INSERT INTO minions (`name`, age, town_id) VALUE (?, ?, ?);");
        addMinion.setString(1, minionName);
        addMinion.setInt(2, minionAge);
        addMinion.setInt(3, townId);
        addMinion.executeUpdate();
        System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);
    }

    private static int setTownIfNotExist(Connection connection, String minionTown) throws SQLException {
        PreparedStatement selectTown = connection.prepareStatement("SELECT id FROM towns" +
                " WHERE `name` LIKE ?;");
        selectTown.setString(1, minionTown);

        ResultSet resultSet = selectTown.executeQuery();

        int townId = 0;
        if (!resultSet.next()) {
            PreparedStatement insertTown = connection.prepareStatement("INSERT INTO towns (`name`) VALUE (?);");
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();
            System.out.printf("Town %s was added to the database.%n", minionTown);
            ResultSet newTownSet = selectTown.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt(1);
        } else {
            townId = resultSet.getInt("id");
        }
        return townId;
    }

    private static int setVillainIfNotExist(Connection connection, String villainName) throws SQLException {
        PreparedStatement selectVillain = connection.prepareStatement("SELECT id FROM villains" +
                " WHERE `name` LIKE ?;");
        selectVillain.setString(1, villainName);

        ResultSet resultSet = selectVillain.executeQuery();

        int villainId = 0;
        if (!resultSet.next()) {
            PreparedStatement insertVillain = connection.prepareStatement(
                    "INSERT INTO villains (`name`, evilness_factor) VALUE (?, ?);");
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");
            insertVillain.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n", villainName);
            ResultSet newVillainSet = selectVillain.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt(1);
        } else {
            villainId = resultSet.getInt("id");
        }
        return villainId;
    }
}
