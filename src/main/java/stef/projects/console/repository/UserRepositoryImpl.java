package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.User;
import stef.projects.console.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {


    private static final String SAVE_USER_QUERY = "insert into \"user\" values (default, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_USER_QUERY = "delete from \"user\" where id = ?";
    private static final String UPDATE_USER_QUERY = "update \"user\" set first_name = ?, last_name = ?, username = ?, email = ?, \"password\" = ?, \"user_role_id\" = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select * from \"user\" as u inner join \"user_role\" as r on u.\"user_role_id\" = r.id where u.id = ?;";
    private static final String SELECT_ALL_QUERY = "select * from \"user\" as u inner join \"user_role\" as r on u.\"user_role_id\" = r.id ;";
    private static final String SELECT_BY_EMAIL_QUERY = "select * from \"user\" as u inner join \"user_role\" as r on u.\"user_role_id\" = r.id where email = ?;";
    private static final String SELECT_BY_USERNAME_AND_PASSWORD_QUERY = "select * from \"user\" as u inner join \"user_role\" as r on u.\"user_role_id\" = r.id where username = ? and \"password\" = ?;";
    private static final String SELECT_BY_USER_ROLE_QUERY = "select * from \"user\" as u inner join \"user_role\" as r on u.\"user_role_id\" = r.id where \"value\" = ?;";
    private static final String SELECT_BY_FIRST_OR_LAST_NAME_QUERY = "select * from \"user\" as u inner join \"user_role\" as r on u.\"user_role_id\" = r.id where first_name = ? or last_name = ?;";
    private PreparedStatement statement;
    private User quizAppUser;
    private List<User> quizAppUserList = new ArrayList<>();
    private boolean successful;


    public static void main(String[] args) throws SQLException {
//        new UserRepositoryImpl().findUserById(3L);
//        new UserRepositoryImpl().deleteUserById(6L);
//        new UserRepositoryImpl().findAllUsers();
//        new UserRepositoryImpl().findUserByEmail("stefanosmichalacos@gmail.com");
//        new UserRepositoryImpl().findUsernameAndPassword("StefanosM", "0000");
//        new UserRepositoryImpl().findUsersByUserRole(UserRole.USER);
//        new UserRepositoryImpl().findUserByFirstNameOrLastName("Stefanos", "Polyzos");
//        new UserRepositoryImpl().saveUser(new User( 0, "Elma", "Michalcou", "ELmaM", "elmamichalacou@gmail.com", "4444", UserRole.USER));
        new UserRepositoryImpl().updateUser(new User( 5, "Dyo", "Karabi", "Palio", "sapiokaravo", "4444", UserRole.USER));

    }

    @Override
    public Boolean saveUser(User user) throws SQLException {
        System.out.println("SAVE_USER_QUERY");
        statement = dbConnection(SAVE_USER_QUERY);
        extractStatement(user, false);
        int update = statement.executeUpdate();
        if (update == 1) {
            successful = true;
        }
        return successful;
    }

    @Override
    public Boolean updateUser(User user) throws SQLException {

        System.out.println("UPDATE_USER_QUERY");
        statement = dbConnection(UPDATE_USER_QUERY);
        extractStatement(user,true);
        int update = statement.executeUpdate();
        if (update == 1) {
            successful = true;
        }
        return successful;
    }



    @Override
    public Boolean deleteUserById(Long id) throws SQLException {

        System.out.println("DELETE_USER_QUERY");
        statement = dbConnection(DELETE_USER_QUERY);
        statement.setLong(1, id);
        int update = statement.executeUpdate();
        if (update == 1) {
            successful = true;
        }
        return successful;
    }
    // we have to make sure to erase all other records associated with this user.

    @Override
    public List<User> findAllUsers() throws SQLException {
        System.out.println("SELECT_ALL_QUERY");
        statement = dbConnection(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            showUser(resultSet);
            quizAppUser = extractUser(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("value"));
            quizAppUserList.add(quizAppUser);
        }
        return quizAppUserList;
    }

    @Override
    public User findUserById(Long id) throws SQLException {
        System.out.println("SELECT_BY_ID_QUERY");
        statement = dbConnection(SELECT_BY_ID_QUERY);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            showUser(resultSet);
            quizAppUser = extractUser(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("value"));
        }
        return quizAppUser;
    }


    @Override
    public User findUserByEmail(String email) throws SQLException {
        System.out.println("SELECT_BY_EMAIL_QUERY");
        statement = dbConnection(SELECT_BY_EMAIL_QUERY);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            showUser(resultSet);
            quizAppUser = extractUser(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("value"));
        }
        return quizAppUser;
    }

    @Override
    public User findUsernameAndPassword(String username, String password) throws SQLException { //find or validate
        System.out.println("SELECT_BY_USERNAME_AND_PASSWORD_QUERY");
        statement = dbConnection(SELECT_BY_USERNAME_AND_PASSWORD_QUERY);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            showUser(resultSet);
            quizAppUser = extractUser(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("value"));
        }
        return quizAppUser;
    }

    @Override
    public List<User> findUsersByUserRole(UserRole userRole) throws SQLException {
        System.out.println("SELECT_BY_USER_ROLE_QUERY");
        statement = dbConnection(SELECT_BY_USER_ROLE_QUERY);
        statement.setString(1, UserRole.getValue(userRole));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            showUser(resultSet);
            quizAppUser = extractUser(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("value"));
            quizAppUserList.add(quizAppUser);
        }
        return quizAppUserList;
    }

    @Override
    public List<User> findUserByFirstNameOrLastName(String fistName, String lastName) throws SQLException {
        System.out.println("SELECT_BY_FIRST_OR_LAST_NAME_QUERY");
        statement = dbConnection(SELECT_BY_FIRST_OR_LAST_NAME_QUERY);
        statement.setString(1, fistName);
        statement.setString(2, lastName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            showUser(resultSet);
            quizAppUser = extractUser(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("value"));
            quizAppUserList.add(quizAppUser);
        }
        return quizAppUserList;
    }

    private void extractStatement(User user, boolean update) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setLong(6, UserRole.getNumberValue(user.getUserRole()));
        if (update){
            statement.setLong(7, user.getId());
        }
    }

    private void showUser(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.print(resultSet.getString("first_name") + ", ");
        System.out.print(resultSet.getString("last_name") + ", ");
        System.out.print(resultSet.getString("username") + ", ");
        System.out.print(resultSet.getString("email") + ", ");
        System.out.print(resultSet.getString("password") + " ");
        System.out.println(resultSet.getString("value") + " ");
    }

    private PreparedStatement dbConnection(String query) throws SQLException {
        return DatabaseConnection.getConnection().prepareStatement(query);
    }

    private User extractUser(long id, String first_name, String last_name, String username, String email, String password, String userRole) {
        return new User(id, first_name, last_name, username, email, password, UserRole.reverseValue(userRole));
    }
}


