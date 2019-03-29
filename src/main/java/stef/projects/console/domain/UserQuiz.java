package stef.projects.console.domain;

public class UserQuiz {
    private Long id;
    private Long userId;
    private Long quizId;
    private Double score;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public Double getScore() {
        return score;
    }

    public UserQuiz(Long id, Long userId, Long quizId, Double score) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
    }
}
