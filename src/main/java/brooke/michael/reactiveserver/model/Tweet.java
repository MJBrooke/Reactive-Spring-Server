package brooke.michael.reactiveserver.model;

import java.time.LocalDate;

public class Tweet {
    private long id;
    private LocalDate dateOfCreation;
    private String content;

    public Tweet(long id, LocalDate dateOfCreation, String content) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
