package dao;

import model.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> getAll();

    Question add(String text);

    Question get(Integer id);

    void remove(Integer id);
}
