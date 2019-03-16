package stef.projects.console.repository;

import stef.projects.console.domain.UserRole;
import stef.projects.console.domain.User;

import java.util.List;

public interface UserRepository {

     Boolean saveUser(User user);

     Boolean updateUser(User user);

     Boolean deleteUserById(Long id);

     List<User> findAllUsers();

     User findUserNyEmail(String email);

     User findUsernameAndPassword(String username, String password);

     List<User> findUsersByUserRole(UserRole userRole);

     List<User> findByFirstNameOrLastName(String fistName, String lastName);
}
