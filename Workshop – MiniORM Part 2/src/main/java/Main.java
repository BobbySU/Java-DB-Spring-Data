import entities.User;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException, InstantiationException {

        createConnection("root", "", "custom-orm");
        Connection connection = getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);

        User user = new User("bobby", 20, LocalDate.now());

        userEntityManager.persist(user);

        User user1 = userEntityManager.findFirst(User.class,"age > 15");

        System.out.println(user1);

        Iterable<User> first = userEntityManager.find(User.class);
        System.out.println(first.toString());

//        User userToDelete= userEntityManager.findFirst(User.class,"age > 15");
//        System.out.println(userToDelete);
//        userEntityManager.delete(userToDelete);

        Iterable<User> second= userEntityManager.find(User.class);
        System.out.println(second.toString());
    }
}
