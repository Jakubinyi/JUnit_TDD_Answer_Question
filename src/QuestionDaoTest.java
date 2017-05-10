import dao.MemoryQuestionDao;
import dao.QuestionDao;
import model.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionDaoTest {

    @Test
    public void testGetAll() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        // when
        List<Question> questions = dao.getAll();

        // then
        assertNotNull(questions);
        assertEquals(0, questions.size());

        // given
        dao.add("123");
        dao.add("234");
        dao.add("345");

        // when
        questions = dao.getAll();

        // then
        assertEquals(3, questions.size());
    }

    @Test
    public void testAdd() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        // when
        Question q = dao.add("123");

        // then
        assertNotNull(q);
        assertNotNull(q.getId());
        assertEquals("123", q.getText());
    }


    @Test
    public void testAddNull() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        // when/then
        assertThrows(IllegalArgumentException.class, () -> {
            dao.add(null);
        });
    }

    @Test
    public void testGet() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        Question newQuestion = dao.add("123");

        // when
        Question fetchedQuestion = dao.get(newQuestion.getId());

        // then
        assertEquals(newQuestion, fetchedQuestion);
    }

    @Test
    public void testGetNull() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.get(null);
        });
    }

    @Test
    public void testGetNonExistent() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.get(Integer.MIN_VALUE);
        });
    }

    @Test
    public void testRemove() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        dao.add("123");
        Question q = dao.add("234");
        dao.add("345");

        // when
        dao.remove(q.getId());

        // then
        assertEquals(2, dao.getAll().size());
    }

    @Test
    public void testRemoveNull() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.remove(null);
        });
    }

    @Test
    public void testRemoveNonExistent() {
        // given
        QuestionDao dao = new MemoryQuestionDao();

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            dao.remove(Integer.MIN_VALUE);
        });
    }
}
