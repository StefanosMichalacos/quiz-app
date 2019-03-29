package stef.projects.console.domain;

public class QuizQuestion {
    private Long id;
    private Long quizId;
    private Long questionId;

    public Long getId() {
        return id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public QuizQuestion(Long id, Long quizId, Long questionId) {
        this.id = id;
        this.quizId = quizId;
        this.questionId = questionId;
    }
}
