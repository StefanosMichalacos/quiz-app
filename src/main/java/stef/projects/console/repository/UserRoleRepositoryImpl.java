package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleRepositoryImpl implements GenericRepository<UserRole , Long>, UserRoleRepository{
    private static final String SAVE_QUERY = "insert into \"user_role\" values (default, ?);";
    private static final String DELETE_BY_ID_QUERY = "delete from \"user_role\" where id = ?";
    private static final String UPDATE_QUERY = "update \"user_role\" set description = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select * from \"user_role\" where id = ?;";
    private static final String SELECT_ALL_QUERY = "select * from \"user_role\"  ;";
    private static final String SELECT_BY_USER_ROLE_QUERY = "select * from \"user_role\" where \"value\" = ?;";


    @Override
    public boolean insert(UserRole userRole) throws SQLException {
//        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SAVE_QUERY);
//        fillPreparedStatement(userRole, statement, false);
//        return DatabaseConnection.extractStatus(statement.executeUpdate());
        return false;
    }

    @Override
    public boolean update(UserRole userRole) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(UPDATE_QUERY);
        fillPreparedStatement(userRole, statement, true);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(DELETE_BY_ID_QUERY);
        statement.setLong(1, aLong);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public List<UserRole> findAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        List<UserRole> userRoleList = new ArrayList<>();
        while (resultSet.next()) {
            showUserRole(resultSet);
            UserRole userRole = bringUserRole(resultSet);
            userRoleList.add(userRole);
        }
        return userRoleList;
    }

    @Override
    public UserRole findById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_ID_QUERY);
        statement.setLong(1, aLong);
        ResultSet resultSet = statement.executeQuery();
        UserRole userRole = null;
        while (resultSet.next()) {
            showUserRole(resultSet);
            userRole = bringUserRole(resultSet);;
        }
        return userRole;
    }

    private void fillPreparedStatement(UserRole userRole, PreparedStatement statement, boolean shouldUpdate) throws SQLException {
        statement.setString(1, UserRole.getValue(userRole));
        if (shouldUpdate) {
            statement.setLong(2, UserRole.getId(userRole));
        }
    }

    private void showUserRole(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.println(resultSet.getString("value"));

    }

    private UserRole bringUserRole(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        return UserRole.reverseId(id);
    }

    @Override
    public List<UserRole> findByUserRole(String value) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_USER_ROLE_QUERY);
        statement.setString(1, value);
        ResultSet resultSet = statement.executeQuery();
        List<UserRole> userRoleList = new ArrayList<>();
        while (resultSet.next()) {
            showUserRole(resultSet);
            UserRole userRole = bringUserRole(resultSet);
            userRoleList.add(userRole);
        }
        return userRoleList;
    }
}
