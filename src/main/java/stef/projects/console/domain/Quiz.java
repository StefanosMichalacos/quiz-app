package stef.projects.console.domain;

public class Quiz {
    private Long id;
    private String description;


    public Quiz(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
