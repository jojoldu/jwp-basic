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
import java.util.List;

import static next.controller.UserSessionUtils.isLogined;

/**
 * Created by jojoldu@gmail.com on 2016-07-21.
 */
public class DeleteQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(DeleteQuestionController.class);

    private QuestionDao questionDao;
    private AnswerDao answerDao;

    public boolean canDelete(long questionId){
        Question question = questionDao.findById(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        if(answers == null || answers.size() == 0){
            return true;
        }

        for(Answer answer : answers){
            if(!answer.getWriter().equals(question.getWriter())){
                return false;
            }
        }

        return true;
    }

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        return jspView("redirect:/");
    }
}
