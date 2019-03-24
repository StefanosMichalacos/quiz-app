package stef.projects.console.repository;

import stef.projects.console.domain.UserRole;
import stef.projects.console.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

     Boolean saveUser(User user) throws SQLException;

     Boolean updateUser(User user) throws SQLException;

     Boolean deleteUserById(Long id) throws SQLException;

     List<User> findAllUsers() throws SQLException;

     User findUserById (Long id) throws SQLException;

     User findUserByEmail(String email) throws SQLException;

     User findUsernameAndPassword(String username, String password) throws SQLException;

     List<User> findUsersByUserRole(UserRole userRole) throws SQLException;

     List<User> findUserByFirstNameOrLastName(String fistName, String lastName) throws SQLException;
}
