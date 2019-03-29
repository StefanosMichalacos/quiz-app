package stef.projects.console.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository <T,ID> {


    boolean insert(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean deleteById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    T findById(ID id) throws SQLException;
}
