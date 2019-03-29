package stef.projects.console.repository;

import stef.projects.console.domain.Choice;
import stef.projects.console.domain.Question;
import stef.projects.console.domain.QuestionChoice;
import stef.projects.console.domain.Quiz;

import java.sql.SQLException;
import java.util.List;

public interface QuestionChoiceRepository extends GenericRepository<QuestionChoice, Long> {

     List<QuestionChoice> findByQuestion(Question question) throws SQLException;

     List<QuestionChoice> findByChoice(Choice choice) throws SQLException;

     List<QuestionChoice> findByCorrectOrNot(boolean correct) throws SQLException;

}
