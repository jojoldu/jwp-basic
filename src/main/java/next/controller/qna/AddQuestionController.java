package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by jojoldu@gmail.com on 2016-07-21.
 */
public class AddQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        //questionId, writer, title, contents, createdDate, countOfAnswer
        Question question = new Question(questionDao.findLastIndex()+1,
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                new Date(),
                0);
        log.debug("question : {}", question);
        questionDao.insert(question);
        return jspView("redirect:/");
    }
}
