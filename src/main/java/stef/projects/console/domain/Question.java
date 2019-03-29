package stef.projects.console.domain;

public class Question {
    private Long id;
    private String description;
    private Long level;

    public Question(Long id, String description, Long level) {
        this.id = id;
        this.description = description;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Long getLevel() {
        return level;
    }
}
