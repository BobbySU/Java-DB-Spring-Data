package orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbContext<E> {
    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    Iterable<E> find(Class<E> table, String where) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException;

    E findFirst(Class<E> table) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    E findFirst(Class<E> table, String where) throws SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException;

}
