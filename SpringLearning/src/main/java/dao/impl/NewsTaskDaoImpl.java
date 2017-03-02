package dao.impl;

import Entity.NewsTask;
import dao.NewsTaskDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/2
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */

/**
 * 数据库操作方法集合；
 */
@Component("newsTaskDao")
public class NewsTaskDaoImpl implements NewsTaskDao {

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Override
    public List<NewsTask> getTasks() {
        String sql = "SELECT task_id,\n" +
                "       site_id,\n" +
                "       task_name,\n" +
                "       task_disc,\n" +
                "       start_Date,\n" +
                "       end_Date,\n" +
                "       pprate,\n" +
                "       keyword,\n" +
                "       rd_keyword,\n" +
                "       no_keyword,\n" +
                "       task_status,\n" +
                "       update_date\n" +
                "       FROM kbyq_topic_task_info WHERE task_status='" + NewsTask.TASK_STATUS_RUNNING + "' ORDER BY task_id DESC";
        return jdbcTemplate.query(sql, new NewsTask());
    }

    @Override
    public List<NewsTask> getTasksByTaskId(String taskId) {
        return null;
    }

    @Override
    public int updateTaskStatus(String taskid, String status) {
        return 0;
    }

    @Override
    public int closeTask(String taskid, String date, Date enddate) {
        return 0;
    }

    @Override
    public int updateUpdateDate(String taskid, String date) {
        return 0;
    }


}
