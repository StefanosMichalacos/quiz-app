package stef.projects.console.domain;

public class  Choice {
    private Long id;
    private String description;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Choice(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
