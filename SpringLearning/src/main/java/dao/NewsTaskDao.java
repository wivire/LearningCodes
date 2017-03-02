package dao;

import Entity.NewsTask;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * User: zhengzhi
 * Date: 2017/3/2
 * Time: 9:20
 * To change this template use File | Settings | File Templates.
 * </pre>
 *
 * @author Administrator
 */
public interface NewsTaskDao {

    public List<NewsTask> getTasks();

    public List<NewsTask> getTasksByTaskId(String taskId);

    public int updateTaskStatus(String taskid, String status);

    public int closeTask(String taskid, String date, Date enddate);

    public int updateUpdateDate(String taskid, String date);

}
