package stef.projects.console.repository;

import stef.projects.console.domain.UserRole;
import stef.projects.console.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends GenericRepository<User, Long>{

     User findUserByEmail(String email) throws SQLException;

     User findUsernameAndPassword(String username, String password) throws SQLException;

     List<User> findUsersByUserRole(UserRole userRole) throws SQLException;

     List<User> findUserByFirstNameOrLastName(String fistName, String lastName) throws SQLException;
}
