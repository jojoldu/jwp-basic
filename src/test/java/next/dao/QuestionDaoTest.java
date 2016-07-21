package next.dao;

import core.jdbc.ConnectionManager;
import next.controller.qna.DeleteQuestionController;
import next.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2016-07-21.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionDaoTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private AnswerDao answerDao;

    @InjectMocks
    private DeleteQuestionController deleteQuestionController;

    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void test_insert() throws Exception{
        Question expected = new Question(10, "자바지기",
                "국내에서 Ruby on Rails와 Play가 활성화되기 힘든 이유는 뭘까?",
                "Ruby on Rails(이하 RoR)는 2006년 즈음에 정말 뜨겁게 달아올랐다가 금방 가라 앉았다. Play 프레임워크는 정말 한 순간 잠시 눈에 뜨이다가 사라져 버렸다. RoR과 Play 기반으로 개발을 해보면 정말 생산성이 높으며, 웹 프로그래밍이 재미있기까지 하다. Spring MVC + JPA(Hibernate) 기반으로 진행하면 설정할 부분도 많고, 기본으로 지원하지 않는 기능도 많아 RoR과 Play에서 기본적으로 지원하는 기능을 서비스하려면 추가적인 개발이 필요하다.",
                new Date(), 0);
        Question result = new QuestionDao().insert(expected);
        assertThat(result.getWriter(), is("자바지기"));
    }

    @Test
    public void test_canDelete() throws Exception {
        assertThat(deleteQuestionController.canDelete(7), is(true));
    }

    @Test
    public void test_delete() throws Exception {

    }
}
