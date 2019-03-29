package stef.projects.console.repository;

import stef.projects.console.config.DatabaseConnection;
import stef.projects.console.domain.Choice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoiceRepositoryImpl implements GenericRepository<Choice, Long>,DescriptionRepository<Choice> {

    private static final String INSERT_QUERY = "insert into \"choice\" values (default, ?);";
    private static final String DELETE_BY_ID_QUERY = "delete from \"choice\" where id = ?";
    private static final String UPDATE_QUERY = "update \"choice\" set description = ? where id = ?;";
    private static final String SELECT_BY_ID_QUERY = "select * from \"choice\" where id = ?;";
    private static final String SELECT_ALL_QUERY = "select * from \"choice\"  ;";
    private static final String SELECT_BY_KEY_WORD_QUERY = "select * from \"choice\" where description ilike ?";


    @Override
    public boolean insert(Choice choice) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(INSERT_QUERY);
        fillPreparedStatement(choice, statement, false);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean update(Choice choice) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(UPDATE_QUERY);
        fillPreparedStatement(choice, statement, true);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(DELETE_BY_ID_QUERY);
        statement.setLong(1, aLong);
        return DatabaseConnection.extractStatus(statement.executeUpdate());
    }

    @Override
    public List<Choice> findAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();
        List<Choice> choiceList = new ArrayList<>();
        while (resultSet.next()) {
            showChoice(resultSet);
            Choice choice = extractChoice(resultSet);
            choiceList.add(choice);
        }
        return null;
    }

    @Override
    public Choice findById(Long aLong) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(SELECT_BY_ID_QUERY);
        statement.setLong(1, aLong);
        ResultSet resultSet = statement.executeQuery();
        Choice choice = null;
        while (resultSet.next()) {
            showChoice(resultSet);
            choice = extractChoice(resultSet);;
        }
        return choice;
    }

    @Override
    public List<Choice> findByDescription(String... strings) throws SQLException {
        String query = extractQuery(strings.length);
        PreparedStatement statement = DatabaseConnection.getPreparedStatementFromQuery(query);
        fillDescriptionStatement(statement, strings);
        ResultSet resultSet = statement.executeQuery();
        List<Choice> choiceList = new ArrayList<>();
        while (resultSet.next()) {
            showChoice(resultSet);
            Choice choice = extractChoice(resultSet);
            choiceList.add(choice);
        }
        return choiceList;
    }

    private void fillPreparedStatement(Choice choice, PreparedStatement statement, boolean shouldUpdate) throws SQLException {
        statement.setString(1, choice.getDescription());
        if (shouldUpdate) {
            statement.setLong(2, choice.getId());
        }
    }

    private void showChoice(ResultSet resultSet) throws SQLException {
        System.out.print(resultSet.getLong("id") + ", ");
        System.out.println(resultSet.getString("description"));

    }

    private Choice extractChoice(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String description = resultSet.getString("description");
        return new Choice(id, description);
    }

    private void fillDescriptionStatement(PreparedStatement statement, String[] strings) throws SQLException {
        for (int i = 0; i < strings.length; i++) {
            statement.setString(i + 1, "%"+ strings[i] + "%");
            System.out.println("%"+ strings[i] + "%");
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
