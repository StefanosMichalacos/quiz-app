package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.QuizQuestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizQuestionRepositoryImpl implements GenericRepository<QuizQuestion,Long> {

    private static final String INSERT_QUERY = "insert into \"quiz_question\" values (default, ?, ?);";
    private static final String DELETE_BY_ID_QUERY = "delete from \"quiz_question\" where id = ?";
    private static final String UPDATE_QUERY = "update \"quiz_question\" set quiz_id = ?, question_id = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select a.*, b.description as quiz_description, c.* from \"quiz_question\"as a inner join \"quiz\" as b on a.quiz_id = b.id inner join \"question\" as c on a.question_id = c.id ;";
    private static final String SELECT_ALL_QUERY = "select a.*, b.description as quiz_description, c.* from \"quiz_question\"as a inner join \"quiz\" as b on a.quiz_id = b.id inner join \"question\" as c on a.question_id = c.id ;";


    @Override
    public boolean insert(QuizQuestion quizQuestion) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(INSERT_QUERY);
        fillPreparedStatement(quizQuestion, statement, false);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean update(QuizQuestion quizQuestion) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(UPDATE_QUERY);
        fillPreparedStatement(quizQuestion, statement, true);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(DELETE_BY_ID_QUERY);
        statement.setLong(1, aLong);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public List<QuizQuestion> findAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        List<QuizQuestion> quizQuestionList = new ArrayList<>();
        while (resultSet.next()) {
            showQuizQuestion(resultSet);
            QuizQuestion quizQuestion = extractQuizQuestion(resultSet);
            quizQuestionList.add(quizQuestion);
        }
        return null;
    }

    @Override
    public QuizQuestion findById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_ID_QUERY);
        statement.setLong(1, aLong);
        ResultSet resultSet = statement.executeQuery();
        QuizQuestion quizQuestion = null;
        while (resultSet.next()) {
            showQuizQuestion(resultSet);
            quizQuestion = extractQuizQuestion(resultSet);;
        }
        return quizQuestion;
    }

    private void fillPreparedStatement(QuizQuestion quizQuestion, PreparedStatement statement, boolean shouldUpdate) throws SQLException {
        statement.setLong(1, quizQuestion.getQuizId());
        statement.setLong(2, quizQuestion.getQuestionId());
        if (shouldUpdate) {
            statement.setLong(3, quizQuestion.getId());
        }
    }

    private void showQuizQuestion(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.print(resultSet.getString("quiz_description") + ", ");
        System.out.print(resultSet.getString("description") + ", ");
        System.out.println(resultSet.getLong("level"));
    }

    private QuizQuestion extractQuizQuestion(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long quizId = resultSet.getLong("quiz_id");
        long questionId = resultSet.getLong("question_id");
        return new QuizQuestion(id, quizId, questionId);
    }
}
