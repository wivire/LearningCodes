import Entity.NewsTask;
import dao.NewsTaskDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/2
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public class NewsTaskDaoTest {

    @Resource
    NewsTaskDao newsTaskDao;

    public void run() {
        List<NewsTask> tasks = newsTaskDao.getTasks();

        for (NewsTask task : tasks) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {

        new NewsTaskDaoTest().run();

    }
}
