package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;

public class ShowController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));

		Question question = questionDao.findById(questionId);
		List<Answer>answers = answerDao.findAllByQuestionId(questionId);
		HttpSession session = req.getSession();
		ModelAndView mav = jspView("/qna/show.jsp");
		mav.addObject("question", question);
		mav.addObject("answers", answers);
		if(session.getAttribute("user") != null){
			mav.addObject("loginUser", (User) session.getAttribute("user"));
		}

		return mav;
	}
}
