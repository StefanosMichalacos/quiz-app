package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.QuestionChoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionChoiceRepositoryImpl implements GenericRepository<QuestionChoice,Long> {

    private static final String SAVE_QUERY = "insert into \"question_choice\" values (default, ?, ?, ?);";
    private static final String DELETE_BY_ID_QUERY = "delete from \"question_choice\" where id = ?";
    private static final String UPDATE_QUERY = "update \"question_choice\" set question_id = ?, choice_id = ?, correct_choice = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select qc.*, q.description as question_description, c.* from \"question_choice\"as qc inner join \"question\" as q on qc.question_id = q.id inner join \"choice\" as c on qc.choice_id = c.id where qc.id = ?;";
    private static final String SELECT_ALL_QUERY = "select qc.*, q.description as question_description, c.* from \"question_choice\"as qc inner join \"question\" as q on qc.question_id = q.id inner join \"choice\" as c on qc.choice_id = c.id;";


    @Override
    public boolean insert(QuestionChoice questionChoice) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SAVE_QUERY);
        fillPreparedStatement(questionChoice, statement, false);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean update(QuestionChoice questionChoice) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(UPDATE_QUERY);
        fillPreparedStatement(questionChoice, statement, true);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(DELETE_BY_ID_QUERY);
        statement.setLong(1, aLong);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public List<QuestionChoice> findAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        List<QuestionChoice> questionChoiceList = new ArrayList<>();
        while (resultSet.next()) {
            showQuestionChoice(resultSet);
            QuestionChoice questionChoice = extractQuestionChoice(resultSet);
            questionChoiceList.add(questionChoice);
        }
        return null;
    }

    @Override
    public QuestionChoice findById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_ID_QUERY);
        statement.setLong(1, aLong);
        ResultSet resultSet = statement.executeQuery();
        QuestionChoice questionChoice = null;
        while (resultSet.next()) {
            showQuestionChoice(resultSet);
            questionChoice = extractQuestionChoice(resultSet);;
        }
        return questionChoice;
    }

    private void fillPreparedStatement(QuestionChoice questionChoice, PreparedStatement statement, boolean shouldUpdate) throws SQLException {
        statement.setLong(1, questionChoice.getQuestionId());
        statement.setLong(2, questionChoice.getChoiceId());
        statement.setBoolean(3, questionChoice.getCorrectChoice());
        if (shouldUpdate) {
            statement.setLong(4, questionChoice.getId());
        }
    }

    private void showQuestionChoice(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.print(resultSet.getString("question_description") + ", ");
        System.out.print(resultSet.getString("description") + ", ");
        System.out.println(resultSet.getBoolean("correct_choice"));
    }

    private QuestionChoice extractQuestionChoice(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userId = resultSet.getLong("question_id");
        long quizId = resultSet.getLong("choice_id");
        boolean correctChoice = resultSet.getBoolean("correct_choice");
        return new QuestionChoice(id, userId, quizId, correctChoice);
    }
}
