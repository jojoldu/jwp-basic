package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static next.controller.UserSessionUtils.isLogined;

/**
 * Created by jojoldu@gmail.com on 2016-07-21.
 */
public class AddQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        HttpSession session = req.getSession();
        if(!isLogined(session)){
            return jspView("redirect:/");
        }
        User user = (User)session.getAttribute("user");
        Question question = new Question(questionDao.findLastIndex()+1,
                user.getName(),
                req.getParameter("title"),
                req.getParameter("contents"),
                new Date(),
                0);
        log.debug("question : {}", question);
        questionDao.insert(question);
        return jspView("redirect:/");
    }
}
