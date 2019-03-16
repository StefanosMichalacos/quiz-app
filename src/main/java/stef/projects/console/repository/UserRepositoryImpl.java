package stef.projects.console.repository;

import stef.projects.console.domain.User;
import stef.projects.console.domain.UserRole;

import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    public Boolean saveUser(User user) {
        return null;
    }

    public Boolean updateUser(User user) {
        return null;
    }

    public Boolean deleteUserById(Long id) {
        return null;
    }
        // we have to make sre to erase all other records associated with this user.

    public List<User> findAllUsers() {
        return null;
    }

    public User findUserNyEmail(String email) {
        return null;
    }

    public User findUsernameAndPassword(String username, String password) {
        return null;
    }

    public List<User> findUsersByUserRole(UserRole userRole) {
        return null;
    }

    public List<User> findByFirstNameOrLastName(String fistName, String lastName) {
        return null;
    }
}
