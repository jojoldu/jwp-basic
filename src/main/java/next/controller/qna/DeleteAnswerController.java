package next.controller.qna;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.view.JsonView;
import next.dao.AnswerDao;
import next.model.Result;

public class DeleteAnswerController implements Controller {
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Long answerId = Long.parseLong(req.getParameter("answerId"));
		AnswerDao answerDao = new AnswerDao();
		
		answerDao.delete(answerId);

		ModelAndView mav = new ModelAndView(req, resp, new JsonView());
		mav.addModelData("data", Result.ok());
		return mav;
	}
}
