package dao;

import model.Question;

import java.util.ArrayList;
import java.util.List;

public class MemoryQuestionDao implements QuestionDao {

    private List<Question> questions = new ArrayList<>();

    @Override
    public List<Question> getAll() {
        return questions;
    }

    @Override
    public Question add(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        Question question;
        if (questions.isEmpty()) {
            question = new Question(text);
        } else {
            int lastId = questions
                    .stream()
                    .map(Question::getId)
                    .max(Integer::compareTo)
                    .get();
            int nextId = lastId + 1;
            question = new Question(nextId, text);
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question get(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return questions.stream()
                .filter(question -> question.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void remove(Integer id) {
        Question question = get(id);
        questions.remove(questions.indexOf(question));
    }
}
