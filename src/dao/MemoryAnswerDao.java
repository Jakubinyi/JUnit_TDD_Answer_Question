package dao;

import model.Answer;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class MemoryAnswerDao implements AnswerDao {

    private final List<Answer> answers = new ArrayList<>();

    @Override
    public List<Answer> getAll() {
        return answers;
    }

    @Override
    public Answer add(Question question, String text) {
        if (question == null || text == null) {
            throw new IllegalArgumentException();
        }
        Answer answer;
        if (answers.isEmpty()) {
            answer = new Answer(text, question);
        } else {
            int lastId = answers
                    .stream()
                    .map(Answer::getId)
                    .max(Integer::compareTo)
                    .get();
            int nextId = lastId + 1;
            answer = new Answer(nextId, text, question);
        }
        answers.add(answer);
        return answer;
    }

    @Override
    public Answer get(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return answers.stream()
                .filter(answer -> answer.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void remove(Integer id) {
        Answer answer = get(id);
        answers.remove(answers.indexOf(answer));
    }
}
