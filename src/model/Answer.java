package model;

import java.util.Objects;

public class Answer {

    private Integer id;
    private String text;
    private Question question;

    public Answer(String text, Question question) {
        this(0, text, question);
    }

    public Answer(Integer id, String text, Question question) {
        this.id = id;
        this.text = text;
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(text, answer.text) &&
                Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, question);
    }
}
