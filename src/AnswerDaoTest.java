import dao.AnswerDao;
import dao.MemoryAnswerDao;
import model.Answer;
import model.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerDaoTest {

    @Test
    public void testGetAll() {
        // given
        AnswerDao dao = new MemoryAnswerDao();

        // when
        List<Answer> answers = dao.getAll();

        // then
        assertNotNull(answers);
        assertEquals(0, answers.size());

        // given
        Question question = new Question("something");

        dao.add(question, "123");
        dao.add(question, "234");
        dao.add(question, "345");

        // when
        answers = dao.getAll();

        // then
        answers.stream().forEach(answer -> assertEquals(question, answer.getQuestion()));
        assertEquals(3, answers.size());
    }

    @Test
    public void testAdd() {
        // given
        AnswerDao dao = new MemoryAnswerDao();
        Question question = new Question("something");

        // when
        Answer answer = dao.add(question, "123");

        // then
        assertNotNull(answer);
        assertNotNull(answer.getId());
        assertEquals("123", answer.getText());
        assertEquals(question, answer.getQuestion());
    }

    @Test
    public void testAddNull() {
        // given
        AnswerDao dao = new MemoryAnswerDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.add(null, "123");
        });

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.add(new Question("something"), null);
        });
    }

    @Test
    public void testGet() {
        // given
        AnswerDao dao = new MemoryAnswerDao();
        Question question = new Question("something");
        Answer newAnswer = dao.add(question, "123");

        // when
        Answer fetchedAnswer = dao.get(newAnswer.getId());

        // when
        assertEquals(newAnswer, fetchedAnswer);
    }

    @Test
    public void testGetNull() {
        // given
        AnswerDao dao = new MemoryAnswerDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.get(null);
        });
    }

    @Test
    public void testGetNonExistent() {
        // given
        AnswerDao dao = new MemoryAnswerDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.get(Integer.MIN_VALUE);
        });
    }

    @Test
    public void testRemove() {
        // given
        AnswerDao dao = new MemoryAnswerDao();
        Question question = new Question("something");

        dao.add(question, "123");
        Answer answer = dao.add(question, "234");
        dao.add(question, "345");

        // when
        dao.remove(answer.getId());

        // then
        assertEquals(2, dao.getAll().size());
    }

    @Test
    public void testRemoveNull() {
        // given
        AnswerDao dao = new MemoryAnswerDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.remove(null);
        });
    }

    @Test
    public void testRemoveNonExistent() {
        // given
        AnswerDao dao = new MemoryAnswerDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.remove(Integer.MIN_VALUE);
        });
    }
}
