package stef.projects.console.repository;

import stef.projects.console.domain.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UserRoleRepository {

    List<UserRole> findByUserRole(String value) throws SQLException;
}

