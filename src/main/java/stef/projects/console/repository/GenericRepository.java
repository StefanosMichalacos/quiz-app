package stef.projects.console.repository;

import stef.projects.console.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T> {

    boolean saveUser(User user) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUserById(Long id) throws SQLException;

    List<User> findAllUsers() throws SQLException;

    User findUserById (Long id) throws SQLException;
}
