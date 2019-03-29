package stef.projects.console.domain;

public class QuestionChoice {
    private Long id;
    private Long questionId;
    private Long choiceId;
    private Boolean correctChoice;

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getChoiceId() {
        return choiceId;
    }

    public Boolean getCorrectChoice() {
        return correctChoice;
    }

    public QuestionChoice(Long id, Long questionId, Long choiceId, Boolean correctChoice) {
        this.id = id;
        this.questionId = questionId;
        this.choiceId = choiceId;
        this.correctChoice = correctChoice;
    }
}
