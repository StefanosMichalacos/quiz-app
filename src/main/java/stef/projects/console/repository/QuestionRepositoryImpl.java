package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.Question;
import stef.projects.console.domain.Quiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements GenericRepository<Question,Long> {

    private static final String SAVE_QUERY = "insert into \"question\" values (default, ?);";
    private static final String DELETE_BY_ID_QUERY = "delete from \"question\" where id = ?";
    private static final String UPDATE_QUERY = "update \"question\" set description = ?, level = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select * from \"question\" where id = ?;";
    private static final String SELECT_ALL_QUERY = "select * from \"question\"  ;";


    @Override
    public boolean insert(Question question) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SAVE_QUERY);
        fillPreparedStatement(question, statement, false);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean update(Question question) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(UPDATE_QUERY);
        fillPreparedStatement(question, statement, true);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(DELETE_BY_ID_QUERY);
        statement.setLong(1, aLong);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public List<Question> findAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        List<Question> questionList = new ArrayList<>();
        while (resultSet.next()) {
            showQuestion(resultSet);
            Question question = extractQuestion(resultSet);
            questionList.add(question);
        }
        return null;
    }

    @Override
    public Question findById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_ID_QUERY);
        statement.setLong(1, aLong);
        ResultSet resultSet = statement.executeQuery();
        Question question = null;
        while (resultSet.next()) {
            showQuestion(resultSet);
            question = extractQuestion(resultSet);;
        }
        return question;
    }

    private void fillPreparedStatement(Question question, PreparedStatement statement, boolean shouldUpdate) throws SQLException {
        statement.setString(1, question.getDescription());
        statement.setLong(2, question.getLevel());
        if (shouldUpdate) {
            statement.setLong(3, question.getId());
        }
    }

    private void showQuestion(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.print(resultSet.getString("description")+ ", ");
        System.out.println(resultSet.getLong("level"));

    }

    private Question extractQuestion(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String description = resultSet.getString("description");
        long level = resultSet.getLong("level");
        return new Question(id, description, level);
    }
}
