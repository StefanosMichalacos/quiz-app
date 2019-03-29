package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.Quiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizRepositoryImpl implements QuizRepository {

    private static final String SAVE_QUERY = "insert into \"quiz\" values (default, ?);";
    private static final String DELETE_BY_ID_QUERY = "delete from \"quiz\" where id = ?";
    private static final String UPDATE_QUERY = "update \"quiz\" set description = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select * from \"quiz\" where id = ?;";
    private static final String SELECT_ALL_QUERY = "select * from \"quiz\"  ;";
    private static final String SELECT_BY_KEY_WORD_QUERY = "select * from \"quiz\" where description ilike ?";


    @Override
    public boolean insert(Quiz quiz) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SAVE_QUERY);
        fillPreparedStatement(quiz, statement, false);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean update(Quiz quiz) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(UPDATE_QUERY);
        fillPreparedStatement(quiz, statement, true);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(DELETE_BY_ID_QUERY);
        statement.setLong(1, aLong);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public List<Quiz> findAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        List<Quiz> quizList = new ArrayList<>();
        while (resultSet.next()) {
            showQuiz(resultSet);
            Quiz quiz = extractQuiz(resultSet);
            quizList.add(quiz);
        }
        return null;
    }

    @Override
    public Quiz findById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_ID_QUERY);
        statement.setLong(1, aLong);
        ResultSet resultSet = statement.executeQuery();
        Quiz quiz = null;
        while (resultSet.next()) {
            showQuiz(resultSet);
            quiz = extractQuiz(resultSet);
        }
        return quiz;
    }

    @Override
    public List<Quiz> findQuizByDescription(String... strings) throws SQLException {
        String query = extractQuery(strings.length);
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(query);
        fillDescriptionStatement(statement, strings);
        ResultSet resultSet = statement.executeQuery();
        List<Quiz> quizList = new ArrayList<>();
        while (resultSet.next()) {
            showQuiz(resultSet);
            Quiz quiz = extractQuiz(resultSet);
            quizList.add(quiz);
        }
        return quizList;
    }

    private void fillPreparedStatement(Quiz quiz, PreparedStatement statement, boolean shouldUpdate) throws SQLException {
        statement.setString(1, quiz.getDescription());
        if (shouldUpdate) {
            statement.setLong(2, quiz.getId());
        }
    }

    private void showQuiz(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.println(resultSet.getString("description"));

    }

    private Quiz extractQuiz(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String description = resultSet.getString("description");
        return new Quiz(id, description);
    }

    private void fillDescriptionStatement(PreparedStatement statement, String[] strings) throws SQLException {
        for (int i = 0; i < strings.length; i++) {
            statement.setString(i + 1, "%"+ strings[i].toString() + "%");
            System.out.println("%"+ strings[i].toString() + "%");
        }
    }

    private String extractQuery(int arrayLength){
        String finalPart = ";";
        String loopPart = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < (arrayLength - 1) ; i++) {
            loopPart = stringBuilder.append(" and description ilike ?").toString();
        }
        System.out.println(loopPart);
        String query = SELECT_BY_KEY_WORD_QUERY + loopPart +finalPart;
        System.out.println(query);
        return query;
    }
}
