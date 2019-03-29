package stef.projects.console;

import stef.projects.console.domain.Question;
import stef.projects.console.domain.UserQuiz;
import stef.projects.console.repository.ChoiceRepositoryImpl;
import stef.projects.console.repository.QuestionChoiceRepositoryImpl;
import stef.projects.console.repository.QuestionRepositoryImpl;
import stef.projects.console.repository.QuizQuestionRepositoryImpl;
import stef.projects.console.repository.QuizRepositoryImpl;
import stef.projects.console.repository.UserQuizRepositoryImpl;
import stef.projects.console.repository.UserRoleRepositoryImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        new QuizRepositoryImpl().findQuizByDescription("java:", "warning", "basic");
//        new UserRoleRepositoryImpl().findByUserRole("USER");
//        new UserQuizRepositoryImpl().findAll();
//        new QuestionRepositoryImpl().findAll();
//        new QuizQuestionRepositoryImpl().findAll();
//        new ChoiceRepositoryImpl().findAll();
//        new QuestionChoiceRepositoryImpl().findAll();
        new QuestionChoiceRepositoryImpl().findByCorrectOrNot(false);
//        new ChoiceRepositoryImpl().findAll();
//        new UserRepositoryImpl().deleteUserById(6L);
//        new UserRepositoryImpl().findAllUsers();
//        new UserRepositoryImpl().findUserByEmail("stefanosmichalacos@gmail.com");
//        new UserRepositoryImpl().findUsernameAndPassword("StefanosM", "0000");
//        new UserRepositoryImpl().findUsersByUserRole(UserRole.USER);
//        new UserRepositoryImpl().findUserByFirstNameOrLastName("Stefanos", "Polyzos");
//        new UserRepositoryImpl().saveUser(new User( 0, "Elma", "Michalcou", "ELmaM", "elmamichalacou@gmail.com", "4444", UserRole.USER));
//        new UserRepositoryImpl().updateUser(new User( 5, "Dyo", "Karabi", "Palio", "sapiokaravo", "4444", UserRole.USER));
    }
}
