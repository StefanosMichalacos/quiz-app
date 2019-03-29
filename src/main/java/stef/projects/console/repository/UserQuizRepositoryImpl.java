package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.UserQuiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserQuizRepositoryImpl implements GenericRepository<UserQuiz,Long> {

    private static final String SAVE_QUERY = "insert into \"user_quiz\" values (default, ?, ?, ?);";
    private static final String DELETE_BY_ID_QUERY = "delete from \"user_quiz\" where id = ?";
    private static final String UPDATE_QUERY = "update \"user_quiz\" set user_id = ?, quiz_id = ?, score = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select * from \"user_quiz\"as uq inner join \"user\" as u on uq.user_id = u.id inner join \"quiz\" as q on uq.quiz_id = q.id where uq.id = ?;";
    private static final String SELECT_ALL_QUERY = "select * from \"user_quiz\"as uq inner join \"user\" as u on uq.user_id = u.id inner join \"quiz\" as q on uq.quiz_id = q.id  ;";


    @Override
    public boolean insert(UserQuiz userQuiz) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SAVE_QUERY);
        fillPreparedStatement(userQuiz, statement, false);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean update(UserQuiz userQuiz) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(UPDATE_QUERY);
        fillPreparedStatement(userQuiz, statement, true);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(DELETE_BY_ID_QUERY);
        statement.setLong(1, aLong);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public List<UserQuiz> findAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        List<UserQuiz> userQuizList = new ArrayList<>();
        while (resultSet.next()) {
            showUserQuiz(resultSet);
            UserQuiz userQuiz = extractUserQuiz(resultSet);
            userQuizList.add(userQuiz);
        }
        return null;
    }

    @Override
    public UserQuiz findById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_ID_QUERY);
        statement.setLong(1, aLong);
        ResultSet resultSet = statement.executeQuery();
        UserQuiz userQuiz = null;
        while (resultSet.next()) {
            showUserQuiz(resultSet);
            userQuiz = extractUserQuiz(resultSet);;
        }
        return userQuiz;
    }

    private void fillPreparedStatement(UserQuiz userQuiz, PreparedStatement statement, boolean shouldUpdate) throws SQLException {
        statement.setLong(1, userQuiz.getUserId());
        statement.setLong(2, userQuiz.getQuizId());
        statement.setDouble(3, userQuiz.getScore());
        if (shouldUpdate) {
            statement.setLong(4, userQuiz.getId());
        }
    }

    private void showUserQuiz(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.print(resultSet.getString("first_name") + ", ");
        System.out.print(resultSet.getString("last_name") + ", ");
        System.out.print(resultSet.getString("username") + ", ");
        System.out.print(resultSet.getString("email") + ", ");
        System.out.print(resultSet.getString("description")+", ");
        System.out.println(resultSet.getDouble("score")+ "%");

    }

    private UserQuiz extractUserQuiz(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userId = resultSet.getLong("user_id");
        long quizId = resultSet.getLong("quiz_id");
        double score = resultSet.getDouble("score");
        return new UserQuiz(id, userId, quizId, score);
    }
}
