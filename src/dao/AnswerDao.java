package dao;

import model.Answer;
import model.Question;

import java.util.List;

public interface AnswerDao {

    List<Answer> getAll();

    Answer add(Question question, String text);

    Answer get(Integer id);

    void remove(Integer id);
}
